package com.epicteam1.skimountains.feature_ski_places.domain.repository

import com.epicteam1.skimountains.feature_ski_places.core.EMPTY
import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace

interface SkiPlaceRepository {

    suspend fun getSkiPlaceById(skiPlaceId: String): SkiPlace

    suspend fun saveSkiPlace(skiPlace: SkiPlace)

    suspend fun getAllSkiPlaces(filterString: String = String.EMPTY): List<SkiPlace>

    suspend fun getAllSkiPlacesSaved(): List<SkiPlace>

    suspend fun deleteSkiPlace(skiPlace: SkiPlace)

    suspend fun reloadSkiPlaces()
}
