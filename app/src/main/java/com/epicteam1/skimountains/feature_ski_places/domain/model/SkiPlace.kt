package com.epicteam1.skimountains.feature_ski_places.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

// 32 поля -> 10 полей
@Entity(tableName = "ski_place_local_table")
data class SkiPlace(

    @PrimaryKey(autoGenerate = false)
    val skiPlaceId: String = "",
    val nameRus: String = "",
    val regionRus: String = "",
    val regionBig: String = "",
    val mainPic: String = "",

    val technicalData: String = "",
    val descriptionData: String = "",
    val geoData: String = "",

    val webCamera: String = "",
    val webCite: String = "",
    val youTubeLink: String = "",
    val entity: String = "",
) : Serializable