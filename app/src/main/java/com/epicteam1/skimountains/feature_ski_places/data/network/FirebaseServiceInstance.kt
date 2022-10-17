package com.epicteam1.skimountains.feature_ski_places.data.network

import com.google.firebase.firestore.FirebaseFirestore
import com.epicteam1.skimountains.feature_ski_places.domain.util.Constants

class FirebaseServiceInstance {

    companion object {
        suspend fun getAllSkiPlacesList() = FirebaseFirestore.getInstance()
            .collection(Constants.FIREBASE_COLLECTION_NAME)
            .whereEqualTo(Constants.ENTITY, Constants.SKI_PLACE).get()

        suspend fun getSearch(search: String) = FirebaseFirestore.getInstance()
            .collection(Constants.FIREBASE_COLLECTION_NAME).whereArrayContains(search, String).get()
    }

}