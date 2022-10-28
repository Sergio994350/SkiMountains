package com.epicteam1.skimountains.feature_ski_places.domain.usecases.implementations

import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace
import com.epicteam1.skimountains.feature_ski_places.domain.repository.SkiPlaceRepository
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.GetSkiPlaceDetailsUseCase

class GetSkiPlaceDetailsUseCaseImpl(private val skiPlaceRepository: SkiPlaceRepository) : GetSkiPlaceDetailsUseCase {
    override suspend fun execute(skiPlaceId: String): SkiPlace = skiPlaceRepository.getSkiPlaceById(skiPlaceId = skiPlaceId)
}