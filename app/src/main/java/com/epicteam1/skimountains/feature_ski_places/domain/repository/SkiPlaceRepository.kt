package com.epicteam1.skimountains.feature_ski_places.domain.repository

import android.util.Log
import com.epicteam1.skimountains.feature_ski_places.data.local.SkiDatabase
import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace
import com.epicteam1.skimountains.feature_ski_places.domain.util.Constants
import com.epicteam1.skimountains.feature_ski_places.domain.util.Constants.NOT_SAVED
import com.epicteam1.skimountains.feature_ski_places.domain.util.Constants.SAVED
import com.google.firebase.firestore.FirebaseFirestore

class SkiPlaceRepository(
    val skiDatabase: SkiDatabase
) {

    // ============== firebase ==============
    val tag = "ski_place_firebase"

    fun getInitAllSkiPlacesFirebase() {
        val skiObjectsList = ArrayList<SkiPlace>()
        skiDatabase.getSkiDao().deleteAllRecords()
        FirebaseFirestore.getInstance()
            .collection(Constants.FIREBASE_COLLECTION_NAME)
            .whereEqualTo(Constants.ENTITY, Constants.SKI_PLACE).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val place = document.toObject(SkiPlace::class.java)
                    skiDatabase.getSkiDao().upsert(place)
                    skiObjectsList.add(place)
                    Log.d(tag, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(tag, "Error getting documents: ", exception)
            }
    }

    suspend fun getSearchFirebase(search: String) {
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

    fun getSkiPlaceById(id: String) {
        FirebaseFirestore.getInstance()
            .collection(Constants.FIREBASE_COLLECTION_NAME)
            .document(id).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val place = document.toObject(SkiPlace::class.java)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(tag, "Error getting documents: ", exception)
            }
    }


    // database
    fun upsert(skiPlace: SkiPlace) = skiDatabase.getSkiDao().upsert(skiPlace)

    fun saveSkiPlace(skiPlace: SkiPlace) {
        if (skiPlace.isSaved != SAVED) {
            skiPlace.isSaved = SAVED
        }
        skiDatabase.getSkiDao().upsert(skiPlace)
    }

    fun getAllSkiPlaces() = skiDatabase.getSkiDao().getAllSkiPlaces()

    fun getAllSkiPlacesSaved() = skiDatabase.getSkiDao().getSkiPlacesSavedList()

    fun deleteSkiPlace(skiPlace: SkiPlace) {
        if (skiPlace.isSaved == SAVED) {
            skiPlace.isSaved = NOT_SAVED
        }
        skiDatabase.getSkiDao().deleteSkiPlace(skiPlace)
    }

}
