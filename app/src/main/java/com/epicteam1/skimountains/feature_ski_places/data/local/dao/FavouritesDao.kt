package com.epicteam1.skimountains.feature_ski_places.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.epicteam1.skimountains.feature_ski_places.data.local.entities.FavouriteEntity
import com.epicteam1.skimountains.feature_ski_places.data.local.entities.SkiPlaceEntity

@Dao
interface FavouritesDao {

    @Query("SELECT * FROM ski_place_table, favourites_table WHERE favourites_table.skiPlaceId = ski_place_table.skiPlaceId")
    suspend fun getFavouriteSkiPlaces(): List<SkiPlaceEntity>

    @Query("SELECT * FROM ski_place_table WHERE skiPlaceId = :skiPlaceId")
    suspend fun getFavouritePlaceById(skiPlaceId: String): SkiPlaceEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavouritePlace(favouriteEntity: FavouriteEntity)

    @Delete
    suspend fun deleteFavouritePlace(favouriteEntity: FavouriteEntity)

    @Query("DELETE FROM favourites_table")
    suspend fun clearFavourites()
}