package com.epicteam1.skimountains.feature_ski_places.domain.repository

import android.util.Log
import com.epicteam1.skimountains.feature_ski_places.data.local.SkiDatabase
import com.epicteam1.skimountains.feature_ski_places.data.network.FirebaseServiceInstance
import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace

class SkiPlaceRepository(
    val skiDatabase: SkiDatabase
) {

    // ============== firebase ==============
    private val tag = "ski_place_firebase"

    suspend fun getAllSkiPlacesListFb() {
        FirebaseServiceInstance.getAllSkiPlacesList()
            .addOnSuccessListener { documents ->
                val skiObjectsList = ArrayList<SkiPlace>()
                for (document in documents) {
                    val place = document.toObject(SkiPlace::class.java)
                    skiObjectsList.add(place)
                }
                return@addOnSuccessListener
            }
            .addOnFailureListener { exception ->
                Log.w(tag, "Error getting documents: ", exception)
            }
    }

    suspend fun getSearchFb(search: String) {
        FirebaseServiceInstance.getSearch(search)
            .addOnSuccessListener { documents ->
                val skiObjectsList = ArrayList<SkiPlace>()
                for (document in documents) {
                    val place = document.toObject(SkiPlace::class.java)
                    if (search == place.regionBig || search == place.nameRus ||
                        search == place.regionRus
                    ) {
                        skiObjectsList.add(place)
                    }
                }
                return@addOnSuccessListener
            }
            .addOnFailureListener { exception ->
                Log.w(tag, "Error getting documents: ", exception)
            }
    }


    // database
    suspend fun upsert(skiPlace: SkiPlace) = skiDatabase.getSkiDao().upsert(skiPlace)
    suspend fun getAllSkiPlaces() = skiDatabase.getSkiDao().getAllSkiPlaces()
    suspend fun deleteSkiPlace(skiPlace: SkiPlace) =
        skiDatabase.getSkiDao().deleteSkiPlace(skiPlace)
}
