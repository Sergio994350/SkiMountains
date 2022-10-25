package com.epicteam1.skimountains.feature_ski_places.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "ski_place_table")
data class SkiPlace(

    @PrimaryKey(autoGenerate = false)
    val skiPlaceId: String = "",
    val blackTrails: String = "",
    val blueTrails: String = "",
    val cabinLifts: String = "",
    val chairLifts: String = "",
    val citiesNearby: String = "",
    val greenTrails: String = "",
    val heightDiff: String = "",
    val howToGetPic: String = "",
    val howToGetText: String = "",
    val latitude: String = "",
    val longitude: String = "",
    val mainPic: String = "",
    val maxHeight: String = "",
    val maxLengthTrail: String = "",
    val nameEng: String = "",
    val nameRus: String = "",
    val nightRide: String = "",
    val railwayAvail: String = "",
    val rating: String = "",
    val redTrails: String = "",
    val regionBig: String = "",
    val regionEng: String = "",
    val regionRus: String = "",
    val seasonDateBegin: String = "",
    val seasonDateEnd: String = "",
    val sumTrailsLength: String = "",
    val towLifts: String = "",
    val webCamera: String = "",
    val webCite: String = "",
    val youTubeLink: String = "",
    val entity: String = "",
    var isSaved: String = "",
) : Serializable

