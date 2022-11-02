package com.epicteam1.skimountains.feature_ski_places.domain.usecases.implementations

import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace
import com.epicteam1.skimountains.feature_ski_places.domain.repository.SkiPlaceRepository
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.SkiPlacesUseCase

class SkiPlacesUseCaseImpl(private val skiPlaceRepository: SkiPlaceRepository) : SkiPlacesUseCase {

    override suspend fun getAllSkiPlaces(): List<SkiPlace> = skiPlaceRepository.getAllSkiPlaces()

    override suspend fun getSavedSkiPlaces(): List<SkiPlace> = skiPlaceRepository.getAllSkiPlacesSaved()

    override suspend fun getSkiPlaceDetails(skiPlaceId: String): SkiPlace = skiPlaceRepository.getSkiPlaceById(skiPlaceId = skiPlaceId)

    override suspend fun deleteSkiPlace(skiPlace: SkiPlace) = skiPlaceRepository.deleteSkiPlace(skiPlace = skiPlace)

    override suspend fun saveSkiPlace(skiPlace: SkiPlace) = skiPlaceRepository.saveSkiPlace(skiPlace = skiPlace)

    override suspend fun getInitAllSkiPlacesFirebase() = skiPlaceRepository.getInitAllSkiPlacesFirebase()

    override suspend fun getSearchFirebase(search: String) = skiPlaceRepository.getSearchFirebase(search = search)

    override suspend fun upsert(skiPlace: SkiPlace) = skiPlaceRepository.upsert(skiPlace = skiPlace)
}