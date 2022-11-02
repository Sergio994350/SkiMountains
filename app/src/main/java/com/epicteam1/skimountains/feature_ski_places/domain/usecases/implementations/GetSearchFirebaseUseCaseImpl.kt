package com.epicteam1.skimountains.feature_ski_places.domain.usecases.implementations

import com.epicteam1.skimountains.feature_ski_places.domain.repository.SkiPlaceRepository
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.GetSearchFirebaseUseCase

class GetSearchFirebaseUseCaseImpl(private val skiPlaceRepository: SkiPlaceRepository) : GetSearchFirebaseUseCase {
    override suspend fun execute(search: String) = skiPlaceRepository.getSearchFirebase(search = search)
}