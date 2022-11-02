package com.epicteam1.skimountains.feature_ski_places.domain.usecases.implementations

import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace
import com.epicteam1.skimountains.feature_ski_places.domain.repository.SkiPlaceRepository
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.GetAllSkiPlacesUseCase

class GetAllSkiPlacesUseCaseImpl(private val skiPlaceRepository: SkiPlaceRepository) : GetAllSkiPlacesUseCase {
    override suspend fun execute(): List<SkiPlace> = skiPlaceRepository.getAllSkiPlaces()
}