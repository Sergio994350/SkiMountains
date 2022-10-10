package com.epicteam1.skimountains.feature_ski_places.data.network

import com.google.firebase.firestore.FirebaseFirestore
import com.epicteam1.skimountains.feature_ski_places.domain.util.Constants

interface FirebaseServiceInterface {

    fun getAllSkiPlacesList() = FirebaseFirestore.getInstance()
        .collection(Constants.FIREBASE_COLLECTION_NAME)
        .whereEqualTo(Constants.ENTITY, Constants.SKI_PLACE).get()
}