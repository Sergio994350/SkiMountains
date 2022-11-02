package com.epicteam1.skimountains.feature_ski_places.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.epicteam1.skimountains.feature_ski_places.core.Constants
import com.epicteam1.skimountains.feature_ski_places.data.local.dao.SkiDao
import com.epicteam1.skimountains.feature_ski_places.data.local.entities.SkiPlaceEntity

@Database(
    entities = [SkiPlaceEntity::class],
    version = 3,
    exportSchema = false
)
abstract class SkiDatabase : RoomDatabase() {
    abstract fun getSkiDao(): SkiDao

    companion object {
        @Volatile
        private var instance: SkiDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext, SkiDatabase::class.java,
                Constants.LOCAL_DATABASE_NAME
            ).allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
    }
}