package com.epicteam1.skimountains.feature_ski_places.domain.repository

import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace

interface SkiPlaceRepository {

    suspend fun getSkiPlaceById(skiPlaceId: String): SkiPlace

    suspend fun upsert(skiPlace: SkiPlace)

    suspend fun addFavouriteSkiPlace(skiPlace: SkiPlace)

    suspend fun getAllSkiPlaces(): List<SkiPlace>

    suspend fun getFavouriteSkiPlaces(): List<SkiPlace>

    suspend fun deleteFavouriteSkiPlace(skiPlace: SkiPlace)

    suspend fun reloadSkiPlaces()
}
