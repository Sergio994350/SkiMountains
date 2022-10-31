package com.epicteam1.skimountains.feature_ski_places.domain.usecases

import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace

interface GetFilteredSkiPlacesUseCase {
    suspend fun execute(filterString: String): List<SkiPlace>
}