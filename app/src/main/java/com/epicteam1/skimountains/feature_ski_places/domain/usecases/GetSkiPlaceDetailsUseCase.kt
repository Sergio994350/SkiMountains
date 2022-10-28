package com.epicteam1.skimountains.feature_ski_places.domain.usecases

import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace

interface GetSkiPlaceDetailsUseCase {
    suspend fun execute(skiPlaceId: String): SkiPlace
}