package com.epicteam1.skimountains.feature_ski_places.data.network

import com.google.firebase.firestore.DocumentSnapshot

interface FirebaseDataSource {

    suspend fun getCollection(collectionName: String): List<DocumentSnapshot>

    suspend fun getDocument(collectionName: String, documentId: String): DocumentSnapshot
}