package com.epicteam1.skimountains.feature_auth.domain.usecases

import com.epicteam1.skimountains.feature_auth.domain.repository.AuthRepository

class SignUpWithEmailPasswordUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(email: String, password: String) =
        authRepository.signUpWithEmailPassword(email = email, password = password)
}