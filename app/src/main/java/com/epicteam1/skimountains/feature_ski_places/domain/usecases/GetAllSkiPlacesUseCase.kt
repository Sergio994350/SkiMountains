package com.epicteam1.skimountains.feature_ski_places.domain.usecases

import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace

interface GetAllSkiPlacesUseCase {
    suspend fun execute(): List<SkiPlace>
}