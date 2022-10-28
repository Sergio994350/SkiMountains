package com.epicteam1.skimountains.feature_ski_places.di

import androidx.room.Room
import com.epicteam1.skimountains.feature_ski_places.core.Constants
import com.epicteam1.skimountains.feature_ski_places.data.local.database.SkiDatabase
import com.epicteam1.skimountains.feature_ski_places.data.repository.SkiPlaceRepositoryImpl
import com.epicteam1.skimountains.feature_ski_places.domain.repository.SkiPlaceRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val featureSkiPlacesDataModule = module {
    single<SkiDatabase> {
        Room.databaseBuilder(androidApplication(), SkiDatabase::class.java, Constants.LOCAL_DATABASE_NAME).build()
    }

    single<FirebaseFirestore> {Firebase.firestore}
    single<SkiPlaceRepository> { SkiPlaceRepositoryImpl(skiDatabase = get(), firebaseFirestore = get()) }

}