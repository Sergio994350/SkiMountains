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
    fun getAllSkiPlaces(): List<SkiPlaceEntity>

    @Query("SELECT * FROM ski_place_table WHERE skiPlaceId = :skiPlaceEntityId")
    fun getSkiPlaceById(skiPlaceEntityId: String): SkiPlaceEntity

    @Query("SELECT * FROM ski_place_table WHERE isSaved = $SAVED")
    fun getSkiPlacesSavedList(): List<SkiPlaceEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(skiPlaceEntity: SkiPlaceEntity)

    @Delete
    fun deleteSkiPlace(skiPlaceEntity: SkiPlaceEntity)

    @Query("DELETE FROM ski_place_table")
    fun deleteAllRecords()

}