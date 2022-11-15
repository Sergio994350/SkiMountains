package com.epicteam1.skimountains.feature_ski_places.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourites_table")
data class FavouriteEntity(
    @PrimaryKey(autoGenerate = false)
    val skiPlaceId: String = ""
)
