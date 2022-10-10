package com.epicteam1.skimountains.feature_ski_places.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace
import kotlinx.coroutines.flow.Flow

@Dao
interface SkiDao {

    @Query("SELECT * FROM ski_place_local_table")
    suspend fun getSkiPlaces(): Flow<List<SkiPlace>>

    @Query("SELECT * FROM ski_place_local_table WHERE skiPlaceId = :id")
    suspend fun getSkiPlaceById(id: String): SkiPlace

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(skiPlace: SkiPlace)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRecord(skiPlace: SkiPlace)

    @Delete
    suspend fun deleteRecord(skiPlace: SkiPlace)

    @Query("DELETE FROM ski_place_local_table")
    suspend fun deleteAllRecords()

}