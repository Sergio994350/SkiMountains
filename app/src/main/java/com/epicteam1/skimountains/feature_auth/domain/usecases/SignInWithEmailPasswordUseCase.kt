package com.epicteam1.skimountains.feature_auth.domain.usecases

import com.epicteam1.skimountains.feature_auth.domain.repository.AuthRepository

class SignInWithEmailPasswordUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(email: String, password: String) =
        authRepository.signInWithEmailPassword(email = email, password = password)
}