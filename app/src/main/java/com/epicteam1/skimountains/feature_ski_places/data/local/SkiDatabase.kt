package com.epicteam1.skimountains.feature_ski_places.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace

@Database(
    entities = [SkiPlace::class],
    version = 1
)
abstract class SkiDatabase : RoomDatabase() {

    abstract val skiDao: SkiDao

    companion object {
        const val DATABASE_NAME = "ski_place_local_db"
    }
}