package com.epicteam1.skimountains.feature_ski_places.data.repository

import com.epicteam1.skimountains.feature_ski_places.core.Constants
import com.epicteam1.skimountains.feature_ski_places.core.toSkiPlace
import com.epicteam1.skimountains.feature_ski_places.core.toSkiPlaceEntity
import com.epicteam1.skimountains.feature_ski_places.data.local.database.SkiDatabase
import com.epicteam1.skimountains.feature_ski_places.data.network.FirebaseDataSource
import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace
import com.epicteam1.skimountains.feature_ski_places.domain.repository.SkiPlaceRepository

class SkiPlaceRepositoryImpl(
    private val skiDatabase: SkiDatabase,
    private val firebaseDataSource: FirebaseDataSource,
) : SkiPlaceRepository {

    override suspend fun getSkiPlaceById(skiPlaceId: String) =
        skiDatabase.getSkiDao().getSkiPlaceById(skiPlaceId).toSkiPlace()

    override suspend fun saveSkiPlace(skiPlace: SkiPlace) {
        val skiPlaceEntity = skiPlace.toSkiPlaceEntity()
        if (skiPlaceEntity.isSaved != Constants.PRE_SAVED) {
            skiPlaceEntity.isSaved = Constants.PRE_SAVED
        }
        skiDatabase.getSkiDao().upsert(skiPlaceEntity)
    }

    override suspend fun getAllSkiPlaces(filterString: String): List<SkiPlace> {
        if (filterString.isNotEmpty()) {
            return skiDatabase.getSkiDao().getFilteredSkiPlaces(filterString = filterString).map { it.toSkiPlace() }
        }
        val localSkiPlaces = skiDatabase.getSkiDao().getAllSkiPlaces().map { it.toSkiPlace() }
        if (localSkiPlaces.isEmpty()) {
            val skiPlaces = getRemoteSkiPlaces()
            skiDatabase.getSkiDao().insertList(skiPlaces.map { it.toSkiPlaceEntity() })
            return skiPlaces
        }
        return localSkiPlaces
    }

    override suspend fun getAllSkiPlacesSaved() =
        skiDatabase.getSkiDao().getSkiPlacesSavedList().map { it.toSkiPlace() }

    override suspend fun deleteSkiPlace(skiPlace: SkiPlace) {
        val skiPlaceEntity = skiPlace.toSkiPlaceEntity()
        if (skiPlaceEntity.isSaved == Constants.PRE_SAVED) {
            skiPlaceEntity.isSaved = Constants.NOT_SAVED
        }
        skiDatabase.getSkiDao().deleteSkiPlace(skiPlaceEntity)
    }

    override suspend fun reloadSkiPlaces() {
        skiDatabase.getSkiDao().deleteAllRecords()
        val skiPlaces = getRemoteSkiPlaces()
        skiDatabase.getSkiDao().insertList(skiPlaces.map { it.toSkiPlaceEntity() })
    }

    private suspend fun getRemoteSkiPlaces(): List<SkiPlace> = firebaseDataSource
        .getCollection(Constants.FIREBASE_COLLECTION_NAME, Constants.SKIPLACE_NAMERUS)
        .map { it.toSkiPlace() }
}