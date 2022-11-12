package com.epicteam1.skimountains.feature_ski_places.domain.usecases

import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace
import com.epicteam1.skimountains.feature_ski_places.domain.repository.SkiPlaceRepository

class GetSkiPlaceDetailsUseCase(private val skiPlaceRepository: SkiPlaceRepository) {
    suspend fun execute(skiPlaceId: String): SkiPlace = skiPlaceRepository.getSkiPlaceById(skiPlaceId = skiPlaceId)
}