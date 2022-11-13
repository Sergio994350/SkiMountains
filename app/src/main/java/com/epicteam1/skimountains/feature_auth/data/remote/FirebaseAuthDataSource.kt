package com.epicteam1.skimountains.feature_auth.data.remote

import com.google.firebase.auth.FirebaseUser

interface FirebaseAuthDataSource {

    suspend fun signUpWithEmailPassword(email: String, password: String): FirebaseUser?

    suspend fun signInWithEmailPassword(email: String, password: String): FirebaseUser?

    suspend fun signOut(): FirebaseUser?

    suspend fun getUser(): FirebaseUser?

    suspend fun sendPasswordReset(email: String)
}