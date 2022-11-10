package com.epicteam1.skimountains.feature_ski_places.domain.usecases

import com.epicteam1.skimountains.feature_ski_places.domain.repository.SkiPlaceRepository

class ReloadSkiPlacesUseCase(private val skiPlaceRepository: SkiPlaceRepository) {
    suspend fun execute() = skiPlaceRepository.reloadSkiPlaces()
}