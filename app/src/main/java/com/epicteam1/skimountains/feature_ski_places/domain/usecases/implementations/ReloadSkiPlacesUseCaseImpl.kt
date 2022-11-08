package com.epicteam1.skimountains.feature_ski_places.domain.usecases.implementations

import com.epicteam1.skimountains.feature_ski_places.domain.repository.SkiPlaceRepository
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.ReloadSkiPlacesUseCase

class ReloadSkiPlacesUseCaseImpl(private val skiPlaceRepository: SkiPlaceRepository) : ReloadSkiPlacesUseCase {
    override suspend fun execute() = skiPlaceRepository.reloadSkiPlaces()
}