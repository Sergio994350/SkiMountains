package com.epicteam1.skimountains.feature_ski_places.domain.usecases

interface GetSearchFirebaseUseCase {
    suspend fun execute(search: String)
}