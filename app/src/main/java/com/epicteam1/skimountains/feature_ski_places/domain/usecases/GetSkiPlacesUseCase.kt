package com.epicteam1.skimountains.feature_ski_places.domain.usecases

import com.epicteam1.skimountains.feature_ski_places.core.EMPTY
import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace
import com.epicteam1.skimountains.feature_ski_places.domain.repository.SkiPlaceRepository

class GetSkiPlacesUseCase(private val skiPlaceRepository: SkiPlaceRepository) {
    suspend fun execute(filterString: String = String.EMPTY): List<SkiPlace> =
        skiPlaceRepository.getAllSkiPlaces(filterString = filterString.trim().lowercase())
}