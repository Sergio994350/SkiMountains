package com.epicteam1.skimountains.feature_ski_places.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.epicteam1.skimountains.feature_ski_places.domain.model.CategoriesList
import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace
import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlacesList
import com.epicteam1.skimountains.feature_ski_places.domain.repository.SkiPlaceRepository
import com.epicteam1.skimountains.feature_ski_places.domain.util.Resource
import kotlinx.coroutines.launch

class SkiViewModel(
    app: Application,
    val skiPlaceRepository: SkiPlaceRepository
) : AndroidViewModel(app) {

    val search: MutableLiveData<Resource<SkiPlacesList>> = MutableLiveData()
    val filter: MutableLiveData<Resource<SkiPlacesList>> = MutableLiveData()
    val details: MutableLiveData<Resource<SkiPlacesList>> = MutableLiveData()
    val category: MutableLiveData<Resource<CategoriesList>> = MutableLiveData()

    init {
        getCategory() // Upper RecyclerView
        getFilter("Урал") // Lower RecyclerView
    }

    fun getSearch(search: String) = viewModelScope.launch {
//        safeGetSearchSkiPlace(search) // TODO
    }

    fun getCategory() = viewModelScope.launch {
//        safeGetCategory(search) // TODO
    }

    fun getFilter(category: String) = viewModelScope.launch {
//        safeGetFilter(category) // TODO
    }

    fun getDetails(id: String) = viewModelScope.launch {
//        safeGetDetails(id) // TODO
    }

    fun saveSkiPlace(skiPlace: SkiPlace) = viewModelScope.launch {
        skiPlaceRepository.upsert(skiPlace)
    }

    suspend fun getAllSkiPlaces() = skiPlaceRepository.getAllSkiPlaces()

    fun deleteFood(skiPlace: SkiPlace) = viewModelScope.launch {
        skiPlaceRepository.deleteSkiPlace(skiPlace)
    }



}
