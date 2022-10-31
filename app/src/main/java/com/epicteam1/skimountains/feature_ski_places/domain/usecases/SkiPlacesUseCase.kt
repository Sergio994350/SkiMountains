package com.epicteam1.skimountains.feature_ski_places.domain.usecases

import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace

interface SkiPlacesUseCase {
    suspend fun getAllSkiPlaces(): List<SkiPlace>
    suspend fun getSavedSkiPlaces(): List<SkiPlace>
    suspend fun getSkiPlaceDetails(skiPlaceId: String): SkiPlace
    suspend fun deleteSkiPlace(skiPlace: SkiPlace)
    suspend fun saveSkiPlace(skiPlace: SkiPlace)
    suspend fun getFilteredSkiPlacesUseCase(filterString: String): List<SkiPlace>
    suspend fun upsert(skiPlace: SkiPlace)
}