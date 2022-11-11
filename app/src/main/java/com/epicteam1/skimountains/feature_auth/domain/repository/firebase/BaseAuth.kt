package com.epicteam1.skimountains.feature_auth.domain.repository.firebase

import com.google.firebase.auth.FirebaseUser

interface BaseAuth {

    suspend fun signUpWithEmailPassword(email: String, password: String): FirebaseUser?

    suspend fun signInWithEmailPassword(email: String, password: String): FirebaseUser?

    fun signOut(): FirebaseUser?

    fun getUser(): FirebaseUser?

    suspend fun sendPasswordReset(email: String)
}