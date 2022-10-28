package com.epicteam1.skimountains.feature_ski_places.data.network

import com.epicteam1.skimountains.feature_ski_places.core.Constants
import com.google.firebase.firestore.FirebaseFirestore

interface FirebaseService {

    suspend fun getAllSkiPlacesListFb() = FirebaseFirestore.getInstance()
        .collection(Constants.FIREBASE_COLLECTION_NAME)
        .whereEqualTo(Constants.ENTITY, Constants.SKI_PLACE).get()

    suspend fun getSearchFb(search: String) = FirebaseFirestore.getInstance()
        .collection(Constants.FIREBASE_COLLECTION_NAME).whereArrayContains(search, String).get()

    suspend fun getCategoryFb() = FirebaseFirestore.getInstance()
        .collection(Constants.FIREBASE_COLLECTION_NAME)
        .whereEqualTo(Constants.ENTITY, Constants.CATEGORY).get()


}