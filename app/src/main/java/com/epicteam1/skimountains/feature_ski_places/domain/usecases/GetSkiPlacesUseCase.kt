package com.epicteam1.skimountains.feature_ski_places.domain.usecases

import com.epicteam1.skimountains.feature_ski_places.core.EMPTY
import com.epicteam1.skimountains.feature_ski_places.core.isMatchedByNameOrRegion
import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace
import com.epicteam1.skimountains.feature_ski_places.domain.repository.SkiPlaceRepository

class GetSkiPlacesUseCase(private val skiPlaceRepository: SkiPlaceRepository) {
    suspend fun execute(filterString: String = String.EMPTY, skiPlacesList: List<SkiPlace> = emptyList()): List<SkiPlace> {
        if (!(filterString.isEmpty() || skiPlacesList.isEmpty())) {
            return skiPlacesList.filter { skiPlace -> skiPlace.isMatchedByNameOrRegion(filterString) }
        }
        return skiPlaceRepository.getAllSkiPlaces()
    }
}