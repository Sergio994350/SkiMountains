package com.epicteam1.skimountains.feature_ski_places.domain.usecases.implementations

import com.epicteam1.skimountains.feature_ski_places.domain.repository.SkiPlaceRepository
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.GetInitAllSkiPlacesFirebaseUseCase

class GetInitAllSkiPlacesFirebaseUseCaseImpl(private val skiPlaceRepository: SkiPlaceRepository) : GetInitAllSkiPlacesFirebaseUseCase {
    override suspend fun execute() = skiPlaceRepository.getInitAllSkiPlacesFirebase()
}