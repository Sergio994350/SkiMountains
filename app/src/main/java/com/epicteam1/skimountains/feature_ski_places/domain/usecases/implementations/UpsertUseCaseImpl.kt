package com.epicteam1.skimountains.feature_ski_places.domain.usecases.implementations

import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace
import com.epicteam1.skimountains.feature_ski_places.domain.repository.SkiPlaceRepository
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.UpsertUseCase

class UpsertUseCaseImpl(private val skiPlaceRepository: SkiPlaceRepository) : UpsertUseCase{
    override suspend fun execute(skiPlace: SkiPlace) = skiPlaceRepository.upsert(skiPlace = skiPlace)
}