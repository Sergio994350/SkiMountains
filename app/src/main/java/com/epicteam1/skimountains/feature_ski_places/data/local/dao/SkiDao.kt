package com.epicteam1.skimountains.feature_ski_places.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.epicteam1.skimountains.feature_ski_places.core.Constants.SAVED
import com.epicteam1.skimountains.feature_ski_places.data.local.entities.SkiPlaceEntity

@Dao
interface SkiDao {

    @Query("SELECT * FROM ski_place_table")
    suspend fun getAllSkiPlaces(): List<SkiPlaceEntity>

    @Query("SELECT * FROM ski_place_table WHERE skiPlaceId = :skiPlaceEntityId")
    suspend fun getSkiPlaceById(skiPlaceEntityId: String): SkiPlaceEntity

    @Query("SELECT * FROM ski_place_table WHERE isSaved = $SAVED")
    suspend fun getSkiPlacesSavedList(): List<SkiPlaceEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(skiPlaceEntity: SkiPlaceEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(skiPlaceEntityList: List<SkiPlaceEntity>)

    @Delete
    suspend fun deleteSkiPlace(skiPlaceEntity: SkiPlaceEntity)

    @Query("DELETE FROM ski_place_table")
    suspend fun deleteAllRecords()

}