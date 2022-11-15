package com.epicteam1.skimountains.feature_ski_places.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.epicteam1.skimountains.feature_ski_places.data.local.entities.SkiPlaceEntity

@Dao
interface SkiDao {

    @Query("SELECT * FROM ski_place_table")
    suspend fun getAllSkiPlaces(): List<SkiPlaceEntity>

    @Query("SELECT * FROM ski_place_table WHERE skiPlaceId = :skiPlaceEntityId")
    suspend fun getSkiPlaceById(skiPlaceEntityId: String): SkiPlaceEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(skiPlaceEntity: SkiPlaceEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(skiPlaceEntityList: List<SkiPlaceEntity>)

    @Query("DELETE FROM ski_place_table")
    suspend fun deleteAllRecords()

}