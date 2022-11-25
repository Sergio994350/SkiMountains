package com.epicteam1.skimountains.feature_ski_places.data.network

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseDataSourceImpl(private val firebaseFirestore: FirebaseFirestore) : FirebaseDataSource {
    override suspend fun getCollection(collectionName: String, orderFieldName: String): List<DocumentSnapshot> =
        firebaseFirestore.collection(collectionName).orderBy(orderFieldName).get().await().documents

    override suspend fun getDocument(collectionName: String, documentId: String): DocumentSnapshot =
        firebaseFirestore.collection(collectionName).document(documentId).get().await()
}