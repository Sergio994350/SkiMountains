package com.epicteam1.skimountains.feature_auth.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    suspend fun signInWithEmailPassword(email: String, password: String): FirebaseUser?

    suspend fun signUpWithEmailPassword(email: String, password: String): FirebaseUser?

    suspend fun signOut(): FirebaseUser?

    suspend fun getCurrentUser(): FirebaseUser?

    suspend fun sendResetPassword(email: String): Boolean
}