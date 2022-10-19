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

    @Query("SELECT * FROM ski_place_table")
    fun getAllSkiPlaces(): LiveData<List<SkiPlace>>

    @Query("SELECT * FROM ski_place_table WHERE skiPlaceId = :id")
    fun getSkiPlaceById(id: String): SkiPlace

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(skiPlace: SkiPlace)

    @Delete
    fun deleteSkiPlace(skiPlace: SkiPlace)

    @Query("DELETE FROM ski_place_table")
    fun deleteAllRecords()

}