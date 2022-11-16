package com.epicteam1.skimountains.feature_auth.presentation.viewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epicteam1.skimountains.SkiApp
import com.epicteam1.skimountains.feature_auth.domain.usecases.GetCurrentUserUseCase
import com.epicteam1.skimountains.feature_auth.domain.usecases.SendResetPasswordUseCase
import com.epicteam1.skimountains.feature_auth.domain.usecases.SignInWithEmailPasswordUseCase
import com.epicteam1.skimountains.feature_auth.domain.usecases.SignOutUseCase
import com.epicteam1.skimountains.feature_auth.domain.usecases.SignUpWithEmailPasswordUseCase
import com.epicteam1.skimountains.feature_ski_places.core.Constants
import com.epicteam1.skimountains.feature_ski_places.core.Constants.CANNOT_SEND_PASSWORD_RESET
import com.epicteam1.skimountains.feature_ski_places.core.Constants.EMAIL_EMPTY
import com.epicteam1.skimountains.feature_ski_places.core.Constants.EMAIL_HAS_SEND
import com.epicteam1.skimountains.feature_ski_places.core.Constants.LOGOUT_FAILURE_MESSAGE
import com.epicteam1.skimountains.feature_ski_places.core.Constants.LOG_IN_SUCCESS
import com.epicteam1.skimountains.feature_ski_places.core.Constants.LOG_OUT_SUCCESS
import com.epicteam1.skimountains.feature_ski_places.core.Constants.PASSWORD_EMPTY
import com.epicteam1.skimountains.feature_ski_places.core.Constants.PASSWORD_NOT_MATCH
import com.epicteam1.skimountains.feature_ski_places.core.Constants.SIGN_UP_SUCCESS
import com.epicteam1.skimountains.feature_ski_places.core.Util
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val signInWithEmailPasswordUseCase: SignInWithEmailPasswordUseCase,
    private val signUpWithEmailPasswordUseCase: SignUpWithEmailPasswordUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val sendResetPasswordUseCase: SendResetPasswordUseCase
) : ViewModel() {

    private val TAG = "AuthViewModel"

    private val _firebaseUser = MutableLiveData<FirebaseUser?>()
    val currentUser get() = _firebaseUser

    private val eventsChannel = Channel<AllEvents>()
    val allEventsFlow = eventsChannel.receiveAsFlow()

    fun signInUser(email: String, password: String) = viewModelScope.launch {
        when {
            email.isEmpty() -> {
                eventsChannel.send(AllEvents.ErrorCode(EMAIL_EMPTY))
            }
            password.isEmpty() -> {
                eventsChannel.send(AllEvents.ErrorCode(PASSWORD_EMPTY))
            }
            else -> {
                if (!Util.hasInternetConnection(SkiApp.getContext())) {
                    Toast.makeText(SkiApp.getContext(), Constants.NO_INTERNET, Toast.LENGTH_SHORT).show()
                }
                actualSignInUser(email, password)
            }
        }
    }

    fun signUpUser(email: String, password: String, confirmPass: String) = viewModelScope.launch {
        when {
            email.isEmpty() -> {
                eventsChannel.send(AllEvents.ErrorCode(EMAIL_EMPTY))
            }
            password.isEmpty() -> {
                eventsChannel.send(AllEvents.ErrorCode(PASSWORD_EMPTY))
            }
            password != confirmPass -> {
                eventsChannel.send(AllEvents.ErrorCode(PASSWORD_NOT_MATCH))
            }
            else -> {
                if (!Util.hasInternetConnection(SkiApp.getContext())) {
                    Toast.makeText(SkiApp.getContext(), Constants.NO_INTERNET, Toast.LENGTH_SHORT).show()
                }
                actualSignUpUser(email, password)
            }
        }
    }

    private fun actualSignInUser(email: String, password: String) = viewModelScope.launch {
        try {
            if (!Util.hasInternetConnection(SkiApp.getContext())) {
                Toast.makeText(SkiApp.getContext(), Constants.NO_INTERNET, Toast.LENGTH_SHORT).show()
            }
            val user = signInWithEmailPasswordUseCase.execute(email, password)
            user?.let {
                _firebaseUser.postValue(it)
                eventsChannel.send(AllEvents.Message(LOG_IN_SUCCESS))
            }
        } catch (e: Exception) {
            val error = e.toString().split(":").toTypedArray()
            Log.d(TAG, "signInUser: ${error[1]}")
            eventsChannel.send(AllEvents.Error(error[1]))
        }
    }

    private fun actualSignUpUser(email: String, password: String) = viewModelScope.launch {
        try {
            if (!Util.hasInternetConnection(SkiApp.getContext())) {
                Toast.makeText(SkiApp.getContext(), Constants.NO_INTERNET, Toast.LENGTH_SHORT).show()
            }
            val user = signUpWithEmailPasswordUseCase.execute(email, password)
            user?.let {
                _firebaseUser.postValue(it)
                eventsChannel.send(AllEvents.Message(SIGN_UP_SUCCESS))
            }
        } catch (e: Exception) {
            val error = e.toString().split(":").toTypedArray()
            Log.d(TAG, "signInUser: ${error[1]}")
            eventsChannel.send(AllEvents.Error(error[1]))
        }
    }

    fun signOut() = viewModelScope.launch {
        try {
            val user = signOutUseCase.execute()
            user?.let {
                eventsChannel.send(AllEvents.Message(LOGOUT_FAILURE_MESSAGE))
            } ?: eventsChannel.send(AllEvents.Message(LOG_OUT_SUCCESS))

            getCurrentUser()

        } catch (e: Exception) {
            val error = e.toString().split(":").toTypedArray()
            Log.d(TAG, "signInUser: ${error[1]}")
            eventsChannel.send(AllEvents.Error(error[1]))
        }
    }

    fun getCurrentUser() = viewModelScope.launch {
        if (!Util.hasInternetConnection(SkiApp.getContext())) {
            Toast.makeText(SkiApp.getContext(), Constants.NO_INTERNET, Toast.LENGTH_SHORT).show()
        }
        val user = getCurrentUserUseCase.execute()
        _firebaseUser.postValue(user)
    }

    fun verifySendPasswordReset(email: String) {
        if (email.isEmpty()) {
            viewModelScope.launch {
                eventsChannel.send(AllEvents.ErrorCode(EMAIL_EMPTY))
            }
        } else {
            if (!Util.hasInternetConnection(SkiApp.getContext())) {
                Toast.makeText(SkiApp.getContext(), Constants.NO_INTERNET, Toast.LENGTH_SHORT).show()
            }
            sendPasswordResetEmail(email)
        }

    }

    private fun sendPasswordResetEmail(email: String) = viewModelScope.launch {
        try {
            val result = sendResetPasswordUseCase.execute(email)
            if (result) {
                eventsChannel.send(AllEvents.Message(EMAIL_HAS_SEND))
            } else {
                eventsChannel.send(AllEvents.Error(CANNOT_SEND_PASSWORD_RESET))
            }
        } catch (e: Exception) {
            val error = e.toString().split(":").toTypedArray()
            Log.d(TAG, "signInUser: ${error[1]}")
            eventsChannel.send(AllEvents.Error(error[1]))
        }
    }

    sealed class AllEvents {
        data class Message(val message: String) : AllEvents()
        data class ErrorCode(val code: Int) : AllEvents()
        data class Error(val error: String) : AllEvents()
    }
}