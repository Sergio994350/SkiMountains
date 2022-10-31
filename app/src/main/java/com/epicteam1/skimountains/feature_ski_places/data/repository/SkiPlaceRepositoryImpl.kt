package com.epicteam1.skimountains.feature_ski_places.data.repository

import com.epicteam1.skimountains.feature_ski_places.data.local.database.SkiDatabase
import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace
import com.epicteam1.skimountains.feature_ski_places.core.Constants
import com.epicteam1.skimountains.feature_ski_places.core.toSkiPlace
import com.epicteam1.skimountains.feature_ski_places.core.toSkiPlaceEntity
import com.epicteam1.skimountains.feature_ski_places.data.network.FirebaseDataSource
import com.epicteam1.skimountains.feature_ski_places.domain.repository.SkiPlaceRepository
import com.google.firebase.firestore.DocumentSnapshot

class SkiPlaceRepositoryImpl(
    private val skiDatabase: SkiDatabase,
    private val firebaseDataSource: FirebaseDataSource,
) : SkiPlaceRepository {

    override suspend fun getFilteredSkiPlaces(filterString: String) =
        skiDatabase.getSkiDao().getFilteredCollection(filterString).map { it.toSkiPlace() }

    override suspend fun getSkiPlaceById(skiPlaceId: String) =
        skiDatabase.getSkiDao().getSkiPlaceById(skiPlaceId).toSkiPlace()

    override suspend fun upsert(skiPlace: SkiPlace) = skiDatabase.getSkiDao().upsert(skiPlace.toSkiPlaceEntity())

    override suspend fun saveSkiPlace(skiPlace: SkiPlace) {
        if (skiPlace.isSaved != Constants.SAVED) {
            skiPlace.isSaved = Constants.SAVED
        }
        skiDatabase.getSkiDao().upsert(skiPlace.toSkiPlaceEntity())
    }

    override suspend fun getAllSkiPlaces(): List<SkiPlace> {
        val localSkiPlaces = skiDatabase.getSkiDao().getAllSkiPlaces();
        if (localSkiPlaces.isEmpty()) {
            val skiPlacesFromFirebase = firebaseDataSource.getCollection(Constants.FIREBASE_COLLECTION_NAME)
            sendFirebaseDataToLocalData(skiPlacesFromFirebase)
        }
        return skiDatabase.getSkiDao().getAllSkiPlaces().map { it.toSkiPlace() }
    }

    override suspend fun getAllSkiPlacesSaved() = skiDatabase.getSkiDao().getSkiPlacesSavedList().map {it.toSkiPlace()}

    override suspend fun deleteSkiPlace(skiPlace: SkiPlace) {
        if (skiPlace.isSaved == Constants.SAVED) {
            skiPlace.isSaved = Constants.NOT_SAVED
        }
        skiDatabase.getSkiDao().deleteSkiPlace(skiPlace.toSkiPlaceEntity())
    }

    override suspend fun updateSkiPlacesFromFirebase(): List<SkiPlace> {
        val skiPlacesFromFirebase = firebaseDataSource.getCollection(Constants.FIREBASE_COLLECTION_NAME)
        skiDatabase.getSkiDao().deleteAllRecords()
        sendFirebaseDataToLocalData(skiPlacesFromFirebase)
        return skiDatabase.getSkiDao().getAllSkiPlaces().map { it.toSkiPlace() }
    }

    private suspend fun sendFirebaseDataToLocalData(skiPlacesFromFirebase: List<DocumentSnapshot>) {
        for (skiPlace in skiPlacesFromFirebase) {
            skiDatabase
                .getSkiDao()
                .upsert(skiPlaceEntity = skiPlace.toSkiPlaceEntity())
        }
    }
}