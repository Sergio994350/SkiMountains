package com.epicteam1.skimountains.feature_ski_places.domain.usecases

import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace
import com.epicteam1.skimountains.feature_ski_places.domain.repository.SkiPlaceRepository

class DeleteSkiPlaceUseCase(private val skiPlaceRepository: SkiPlaceRepository) {
    suspend fun execute(skiPlace: SkiPlace) = skiPlaceRepository.deleteSkiPlace(skiPlace = skiPlace)
}