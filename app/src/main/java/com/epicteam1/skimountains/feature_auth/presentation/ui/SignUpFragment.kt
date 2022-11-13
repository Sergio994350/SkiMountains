package com.epicteam1.skimountains.feature_auth.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.epicteam1.skimountains.R
import com.epicteam1.skimountains.databinding.FragmentSignUpBinding
import com.epicteam1.skimountains.feature_auth.presentation.viewModel.AuthViewModel
import com.epicteam1.skimountains.feature_ski_places.core.Constants.EMAIL_EMPTY
import com.epicteam1.skimountains.feature_ski_places.core.Constants.PASSWORD_EMPTY
import com.epicteam1.skimountains.feature_ski_places.core.Constants.PASSWORD_NOT_MATCH
import com.epicteam1.skimountains.feature_ski_places.core.Constants.SIGN_UP_SUCCESS
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private val authViewModel by viewModel<AuthViewModel>()
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private val TAG = "SignUpFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        registerObservers()
        listenToChannels()
        binding.apply {
            signupButton.setOnClickListener {
                val email = emailEt.text.toString()
                val password = passET.text.toString()
                val confirmPass = confirmPassEt.text.toString()
                authViewModel.signUpUser(email, password, confirmPass)
                Toast.makeText(context, SIGN_UP_SUCCESS, Toast.LENGTH_SHORT).show()
            }
            tvAlreadyRegistered.setOnClickListener {
                findNavController().navigate(R.id.action_sign_up_to_sign_in_fragment)
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun registerObservers() {
        authViewModel.currentUser.observe(viewLifecycleOwner) { user ->
            user?.let {
                findNavController().navigate(R.id.action_sign_up_to_home_fragment)
            }
        }
    }

    private fun listenToChannels() {
        viewLifecycleOwner.lifecycleScope.launch {
            authViewModel.allEventsFlow.collect { event ->
                when (event) {
                    is AuthViewModel.AllEvents.Error -> {
                        binding.apply {
                            errorTxtSignup.text = event.error
                        }
                    }
                    is AuthViewModel.AllEvents.Message -> {
                        Toast.makeText(requireContext(), event.message, Toast.LENGTH_SHORT).show()
                    }
                    is AuthViewModel.AllEvents.ErrorCode -> {
                        if (event.code == EMAIL_EMPTY)
                            binding.apply {
                                emailEt.error = (R.string.enter_your_email).toString()
                            }

                        if (event.code == PASSWORD_EMPTY)
                            binding.apply {
                                passET.error = (R.string.enter_your_password).toString()
                            }

                        if (event.code == PASSWORD_NOT_MATCH)
                            binding.apply {
                                confirmPassEt.error = (R.string.password_not_match).toString()
                            }
                    }
                    else -> {
                        Log.d(TAG, "listenToChannels: No event received")
                    }
                }
            }
        }
    }
}