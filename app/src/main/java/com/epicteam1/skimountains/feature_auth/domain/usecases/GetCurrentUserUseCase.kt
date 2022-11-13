package com.epicteam1.skimountains.feature_auth.domain.usecases

import com.epicteam1.skimountains.feature_auth.domain.repository.AuthRepository

class GetCurrentUserUseCase(private val authRepository: AuthRepository) {
    suspend fun execute() = authRepository.getCurrentUser()
}