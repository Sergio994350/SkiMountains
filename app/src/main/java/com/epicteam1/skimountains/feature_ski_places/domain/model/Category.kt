package com.epicteam1.skimountains.feature_ski_places.domain.model

import androidx.room.PrimaryKey

data class Category(
    val categoryId: String = "",
    val categoryNameRus: String = "",
    val categoryMainPic: String = "",
    val categoryDescription: String = "",
)
