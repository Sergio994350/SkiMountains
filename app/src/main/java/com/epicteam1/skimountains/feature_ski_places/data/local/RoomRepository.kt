package com.epicteam1.skimountains.feature_ski_places.data.local

import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace
import javax.inject.Inject

class RoomRepository @Inject constructor(private val skiDao: SkiDao) {

    suspend fun insertRecords(skiPlace: SkiPlace) {
        skiDao.insertRecord(skiPlace)
    }

    suspend fun getDataUserById(id: String): SkiPlace {
        return skiDao.getSkiPlaceById(id)
    }

    suspend fun updateRecord(skiPlace: SkiPlace) {
        skiDao.updateRecord(skiPlace)
    }

    suspend fun deleteRecord(skiPlace: SkiPlace) {
        skiDao.deleteRecord(skiPlace)
    }

    suspend fun deleteAllRecord() {
        skiDao.deleteAllRecords()
    }
}