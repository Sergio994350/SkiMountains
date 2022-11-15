package com.epicteam1.skimountains.feature_ski_places.core

import android.content.Context
import com.epicteam1.skimountains.R
import com.epicteam1.skimountains.feature_ski_places.data.local.entities.FavouriteEntity
import com.epicteam1.skimountains.feature_ski_places.data.local.entities.SkiPlaceEntity
import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace
import com.google.firebase.firestore.DocumentSnapshot

fun DocumentSnapshot.toSkiPlace() = SkiPlace(
    data?.get(Constants.SKIPLACE_ID) as String,
    data?.get(Constants.SKIPLACE_BLACKTRAILS) as String,
    data?.get(Constants.SKIPLACE_BLUETRAILS) as String,
    data?.get(Constants.SKIPLACE_CABINLIFTS) as String,
    data?.get(Constants.SKIPLACE_CHAIRLIFTS) as String,
    data?.get(Constants.SKIPLACE_CITIESNEARBY) as String,
    data?.get(Constants.SKIPLACE_GREENTRAILS) as String,
    data?.get(Constants.SKIPLACE_HEIGHTDIFF) as String,
    data?.get(Constants.SKIPLACE_HOWTOGETPIC) as String,
    data?.get(Constants.SKIPLACE_HOWTOGETTEXT) as String,
    data?.get(Constants.SKIPLACE_LATITUDE) as String,
    data?.get(Constants.SKIPLACE_LONGITUDE) as String,
    data?.get(Constants.SKIPLACE_MAINPIC) as String,
    data?.get(Constants.SKIPLACE_MAXHEIGHT) as String,
    data?.get(Constants.SKIPLACE_MAXLENGTHTRAIL) as String,
    data?.get(Constants.SKIPLACE_NAMEENG) as String,
    data?.get(Constants.SKIPLACE_NAMERUS) as String,
    data?.get(Constants.SKIPLACE_NIGHTRIDE) as String,
    data?.get(Constants.SKIPLACE_RAILWAYAVAIL) as String,
    data?.get(Constants.SKIPLACE_RATING) as String,
    data?.get(Constants.SKIPLACE_REDTRAILS) as String,
    data?.get(Constants.SKIPLACE_REGIONBIG) as String,
    data?.get(Constants.SKIPLACE_REGIONENG) as String,
    data?.get(Constants.SKIPLACE_REGIONRUS) as String,
    data?.get(Constants.SKIPLACE_SEASONDATEBEGIN) as String,
    data?.get(Constants.SKIPLACE_SEASONDATEEND) as String,
    data?.get(Constants.SKIPLACE_SUMTRAILSLENGTH) as String,
    data?.get(Constants.SKIPLACE_TOWLIFTS) as String,
    data?.get(Constants.SKIPLACE_WEBCAMERA) as String,
    data?.get(Constants.SKIPLACE_WEBCITE) as String,
    data?.get(Constants.SKIPLACE_YOUTUBELINK) as String,
    data?.get(Constants.ENTITY) as String,
    data?.get(Constants.SKIPLACE_ISSAVED) as String,
)

fun SkiPlaceEntity.toSkiPlace() = SkiPlace(
    skiPlaceId = skiPlaceId,
    blackTrails = blackTrails,
    blueTrails = blueTrails,
    cabinLifts = cabinLifts,
    chairLifts = chairLifts,
    citiesNearby = citiesNearby,
    greenTrails = greenTrails,
    heightDiff = heightDiff,
    howToGetPic = howToGetPic,
    howToGetText = howToGetText,
    latitude = latitude,
    longitude = longitude,
    mainPic = mainPic,
    maxHeight = maxHeight,
    maxLengthTrail = maxLengthTrail,
    nameEng = nameEng,
    nameRus = nameRus,
    nightRide = nightRide,
    railwayAvail = railwayAvail,
    rating = rating,
    redTrails = redTrails,
    regionBig = regionBig,
    regionEng = regionEng,
    regionRus = regionRus,
    seasonDateBegin = seasonDateBegin,
    seasonDateEnd = seasonDateEnd,
    sumTrailsLength = sumTrailsLength,
    towLifts = towLifts,
    webCamera = webCamera,
    webCite = webCite,
    youTubeLink = youTubeLink,
    entity = entity,
    isSaved = isSaved
)

fun SkiPlace.toSkiPlaceEntity() = SkiPlaceEntity(
    skiPlaceId = skiPlaceId,
    blackTrails = blackTrails,
    blueTrails = blueTrails,
    cabinLifts = cabinLifts,
    chairLifts = chairLifts,
    citiesNearby = citiesNearby,
    greenTrails = greenTrails,
    heightDiff = heightDiff,
    howToGetPic = howToGetPic,
    howToGetText = howToGetText,
    latitude = latitude,
    longitude = longitude,
    mainPic = mainPic,
    maxHeight = maxHeight,
    maxLengthTrail = maxLengthTrail,
    nameEng = nameEng,
    nameRus = nameRus,
    nightRide = nightRide,
    railwayAvail = railwayAvail,
    rating = rating,
    redTrails = redTrails,
    regionBig = regionBig,
    regionEng = regionEng,
    regionRus = regionRus,
    seasonDateBegin = seasonDateBegin,
    seasonDateEnd = seasonDateEnd,
    sumTrailsLength = sumTrailsLength,
    towLifts = towLifts,
    webCamera = webCamera,
    webCite = webCite,
    youTubeLink = youTubeLink,
    entity = entity,
    isSaved = isSaved
)

fun SkiPlace.getTechnicalDataRus(context: Context) = context.getString(R.string.technical_data,
    heightDiff, maxHeight, sumTrailsLength, maxLengthTrail, greenTrails, blueTrails, redTrails, blackTrails)

fun SkiPlace.getGeoDataRus(context: Context) =  context.getString(R.string.geo_data, citiesNearby, latitude, longitude)

fun SkiPlace.hasNightRide() = nightRide == Constants.HAS_NIGHTRIDE

fun SkiPlace.hasRailWay() = railwayAvail == Constants.HAS_RAILWAY

fun SkiPlace.getDescriptionDataRus(context: Context) : String {
    val railwayAvailSymb =  if (hasRailWay()) "☑" else "☐"
    val nightRideSymb =  if (hasNightRide()) "☑" else "☐"

    return context.getString(R.string.description_data, towLifts, chairLifts,
        cabinLifts, seasonDateBegin, seasonDateEnd, railwayAvailSymb, nightRideSymb)
}

fun SkiPlace.getExtendedName() = "$nameRus $regionRus"

fun SkiPlace.isMatchedByNameOrRegion(filterString: String): Boolean {

    val isMatchedByName = nameRus.startsWith(filterString, true)
        || nameEng.startsWith(filterString, true)

    val isMMatchedByRegion = regionRus.startsWith(filterString, true)
        || regionEng.startsWith(filterString, true)
        || regionBig.startsWith(filterString, true)

    return isMMatchedByRegion || isMatchedByName
}

fun SkiPlace.toFavouriteEntity() = FavouriteEntity(skiPlaceId = skiPlaceId)

val String.Companion.EMPTY:String get() = ""