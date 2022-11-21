package com.epicteam1.skimountains.feature_ski_places.domain.usecases

import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace

class SortSkiPlaceListUseCase {
    fun execute(skiPlaceList: List<SkiPlace>?, sortOrderAsc: Boolean): List<SkiPlace> {
        if (sortOrderAsc) {
            return skiPlaceList?.let { skiPlaceList.sortedBy { it.nameRus } } ?: emptyList()
        }
        return skiPlaceList?.let { skiPlaceList.sortedByDescending { it.nameRus } } ?: emptyList()
    }
}