package com.epicteam1.skimountains.feature_ski_places.domain.usecases.implementations

import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace
import com.epicteam1.skimountains.feature_ski_places.domain.repository.SkiPlaceRepository
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.GetSavedSkiPlacesUseCase

class GetSavedSkiPlacesUseCaseImpl(private val skiPlaceRepository: SkiPlaceRepository) : GetSavedSkiPlacesUseCase {
    override suspend fun execute(): List<SkiPlace> = skiPlaceRepository.getAllSkiPlacesSaved()
}