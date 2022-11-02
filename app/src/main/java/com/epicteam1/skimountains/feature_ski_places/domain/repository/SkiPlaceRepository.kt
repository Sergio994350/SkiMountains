package com.epicteam1.skimountains.feature_ski_places.domain.repository

import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace

interface SkiPlaceRepository {

    suspend fun getInitAllSkiPlacesFirebase()

    suspend fun getFilteredSkiPlaces(filterString: String): List<SkiPlace>

    suspend fun getSkiPlaceById(skiPlaceId: String): SkiPlace

    suspend fun upsert(skiPlace: SkiPlace)

    suspend fun saveSkiPlace(skiPlace: SkiPlace)

    suspend fun getAllSkiPlaces(): List<SkiPlace>

    suspend fun getAllSkiPlacesSaved(): List<SkiPlace>

    suspend fun deleteSkiPlace(skiPlace: SkiPlace)

}
