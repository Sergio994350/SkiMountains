package com.epicteam1.skimountains.feature_ski_places.domain.usecases.implementations

import com.epicteam1.skimountains.feature_ski_places.domain.repository.SkiPlaceRepository
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.GetFilteredSkiPlacesUseCase

class GetFilteredSkiPlacesUseCaseImpl(private val skiPlaceRepository: SkiPlaceRepository) : GetFilteredSkiPlacesUseCase {
    override suspend fun execute(filterString: String) = skiPlaceRepository.getFilteredSkiPlaces(filterString = filterString)
}