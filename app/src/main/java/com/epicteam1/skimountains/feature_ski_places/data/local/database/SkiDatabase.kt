package com.epicteam1.skimountains.feature_ski_places.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.epicteam1.skimountains.feature_ski_places.data.local.dao.SkiDao
import com.epicteam1.skimountains.feature_ski_places.data.local.entities.SkiPlaceEntity

@Database(
    entities = [SkiPlaceEntity::class],
    version = 3,
    exportSchema = false
)
abstract class SkiDatabase : RoomDatabase() {
    abstract fun getSkiDao(): SkiDao
}