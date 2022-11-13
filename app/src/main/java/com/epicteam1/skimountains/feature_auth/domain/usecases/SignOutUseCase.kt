package com.epicteam1.skimountains.feature_auth.domain.usecases

import com.epicteam1.skimountains.feature_auth.domain.repository.AuthRepository

class SignOutUseCase(private val authRepository: AuthRepository) {
    suspend fun execute() = authRepository.signOut()
}