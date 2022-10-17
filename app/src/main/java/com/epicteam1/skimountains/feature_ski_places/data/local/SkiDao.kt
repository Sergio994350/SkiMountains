package com.epicteam1.skimountains.feature_ski_places.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace

@Dao
interface SkiDao {

    @Query("SELECT * FROM ski_place_local_table")
    suspend fun getAllSkiPlaces(): LiveData<List<SkiPlace>>

    @Query("SELECT * FROM ski_place_local_table WHERE skiPlaceId = :id")
    suspend fun getSkiPlaceById(id: String): SkiPlace

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(skiPlace: SkiPlace)

    @Delete
    suspend fun deleteSkiPlace(skiPlace: SkiPlace)

    @Query("DELETE FROM ski_place_local_table")
    suspend fun deleteAllRecords()

}