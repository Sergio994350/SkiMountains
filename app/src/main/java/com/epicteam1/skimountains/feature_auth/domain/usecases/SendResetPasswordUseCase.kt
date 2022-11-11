package com.epicteam1.skimountains.feature_auth.domain.usecases

import com.epicteam1.skimountains.feature_auth.domain.repository.AuthRepository

class SendResetPasswordUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(email: String) = authRepository.sendResetPassword(email = email)
}