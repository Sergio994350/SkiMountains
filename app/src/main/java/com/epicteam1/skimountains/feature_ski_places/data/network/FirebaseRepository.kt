package com.epicteam1.skimountains.feature_ski_places.data.network

import android.util.Log
import com.epicteam1.skimountains.feature_ski_places.data.local.SkiDao
import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject


class FirebaseRepository @Inject constructor(
    private val firebaseServiceInterface: FirebaseServiceInterface,
    private val skiDao: SkiDao) {

    private val tag = "ski_place_firebase"

    fun getAllSkiPlacesList() = firebaseServiceInterface.getAllSkiPlacesList()
        .addOnSuccessListener { documents ->
            val skiObjectsList = ArrayList<SkiPlace>()
            for (document in documents) {
                val place = document.toObject(SkiPlace::class.java)
                skiObjectsList.add(place)
                Log.d(tag, "${document.id} => ${document.data}")
            }
        }
        .addOnFailureListener { exception ->
            Log.w(tag, "Error getting documents: ", exception)
        }

}

