package com.epicteam1.skimountains.feature_ski_places.domain.usecases.implementations

import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace
import com.epicteam1.skimountains.feature_ski_places.domain.repository.SkiPlaceRepository
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.DeleteSkiPlaceUseCase

class DeleteSkiPlaceUseCaseImpl(private val skiPlaceRepository: SkiPlaceRepository) : DeleteSkiPlaceUseCase {
    override suspend fun execute(skiPlace: SkiPlace) = skiPlaceRepository.deleteSkiPlace(skiPlace = skiPlace)
}