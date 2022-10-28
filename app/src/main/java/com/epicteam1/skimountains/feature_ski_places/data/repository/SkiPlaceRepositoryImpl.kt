package com.epicteam1.skimountains.feature_ski_places.data.repository

import android.util.Log
import com.epicteam1.skimountains.feature_ski_places.data.local.database.SkiDatabase
import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace
import com.epicteam1.skimountains.feature_ski_places.core.Constants
import com.epicteam1.skimountains.feature_ski_places.core.toSkiPlace
import com.epicteam1.skimountains.feature_ski_places.core.toSkiPlaceEntity
import com.epicteam1.skimountains.feature_ski_places.data.network.FirebaseDataSource
import com.epicteam1.skimountains.feature_ski_places.domain.repository.SkiPlaceRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class SkiPlaceRepositoryImpl(
    private val skiDatabase: SkiDatabase,
    private val firebaseDataSource: FirebaseDataSource,
) : SkiPlaceRepository {
    private val tag = "ski_place_firebase"

    override suspend fun getInitAllSkiPlacesFirebase() {
        skiDatabase.getSkiDao().deleteAllRecords()
        val skiPlaces = firebaseDataSource.getCollection(Constants.FIREBASE_COLLECTION_NAME)
            .map { it.toSkiPlace() }

        for (skiPlace in skiPlaces) {
            skiDatabase
                .getSkiDao()
                .upsert(skiPlaceEntity = skiPlace.toSkiPlaceEntity())
        }
    }

    override suspend fun getSearchFirebase(search: String) {
        val skiObjectsList = ArrayList<SkiPlace>()
        FirebaseFirestore.getInstance()
            .collection(Constants.FIREBASE_COLLECTION_NAME)
            .whereEqualTo(Constants.ENTITY, Constants.SKI_PLACE)
            .whereArrayContains(search, String).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val place = document.toObject(SkiPlace::class.java)
                    if (search == place.regionBig || search == place.nameRus ||
                        search == place.regionRus
                    ) {
                        skiObjectsList.add(place)
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w(tag, "Error getting documents: ", exception)
            }
    }

    override suspend fun getSkiPlaceById(skiPlaceId: String) =
        skiDatabase.getSkiDao().getSkiPlaceById(skiPlaceId).toSkiPlace()

    // database
    override suspend fun upsert(skiPlace: SkiPlace) = skiDatabase.getSkiDao().upsert(skiPlace.toSkiPlaceEntity())

    override suspend fun saveSkiPlace(skiPlace: SkiPlace) {
        if (skiPlace.isSaved != Constants.SAVED) {
            skiPlace.isSaved = Constants.SAVED
        }
        skiDatabase.getSkiDao().upsert(skiPlace.toSkiPlaceEntity())
    }

    override suspend fun getAllSkiPlaces() = skiDatabase.getSkiDao().getAllSkiPlaces().map {it.toSkiPlace()}

    override suspend fun getAllSkiPlacesSaved() = skiDatabase.getSkiDao().getSkiPlacesSavedList().map {it.toSkiPlace()}

    override suspend fun deleteSkiPlace(skiPlace: SkiPlace) {
        if (skiPlace.isSaved == Constants.SAVED) {
            skiPlace.isSaved = Constants.NOT_SAVED
        }
        skiDatabase.getSkiDao().deleteSkiPlace(skiPlace.toSkiPlaceEntity())
    }
}