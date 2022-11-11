package com.epicteam1.skimountains.feature_auth.data.repository

import com.epicteam1.skimountains.feature_auth.data.remote.FirebaseAuthDataSource
import com.epicteam1.skimountains.feature_auth.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authenticator : FirebaseAuthDataSource
) : AuthRepository {
    override suspend fun signInWithEmailPassword(email: String, password: String): FirebaseUser? {
        return authenticator.signInWithEmailPassword(email , password)
    }

    override suspend fun signUpWithEmailPassword(email: String, password: String): FirebaseUser? {
        return authenticator.signUpWithEmailPassword(email , password)
    }

    override suspend fun signOut(): FirebaseUser? {
        return authenticator.signOut()
    }

    override suspend fun getCurrentUser(): FirebaseUser? {
        return authenticator.getUser()
    }

    override suspend fun sendResetPassword(email: String): Boolean {
        authenticator.sendPasswordReset(email)
        return true
    }
}