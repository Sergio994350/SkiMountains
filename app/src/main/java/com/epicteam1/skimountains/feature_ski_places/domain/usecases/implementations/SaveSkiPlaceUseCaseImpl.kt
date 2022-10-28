package com.epicteam1.skimountains.feature_ski_places.domain.usecases.implementations

import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace
import com.epicteam1.skimountains.feature_ski_places.domain.repository.SkiPlaceRepository
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.SaveSkiPlaceUseCase

class SaveSkiPlaceUseCaseImpl(private val skiPlaceRepository: SkiPlaceRepository) : SaveSkiPlaceUseCase {
    override suspend fun execute(skiPlace: SkiPlace) = skiPlaceRepository.saveSkiPlace(skiPlace = skiPlace)
}