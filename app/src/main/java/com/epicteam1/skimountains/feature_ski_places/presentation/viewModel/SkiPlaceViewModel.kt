package com.epicteam1.skimountains.feature_ski_places.presentation.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.epicteam1.skimountains.feature_ski_places.core.Constants
import com.epicteam1.skimountains.feature_ski_places.core.Constants.LOAD_FROM_LOCAL_DATABASE
import com.epicteam1.skimountains.feature_ski_places.core.Constants.NO_INTERNET
import com.epicteam1.skimountains.feature_ski_places.core.Util
import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.DeleteSkiPlaceUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.GetSavedSkiPlacesUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.GetSkiPlaceDetailsUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.GetSkiPlacesUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.ReloadSkiPlacesUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.SaveSkiPlaceUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.UpsertUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SkiPlaceViewModel(
    app: Application,
    private val deleteSkiPlaceUseCase: DeleteSkiPlaceUseCase,
    private val getSkiPlacesUseCase: GetSkiPlacesUseCase,
    private val getSavedSkiPlacesUseCase: GetSavedSkiPlacesUseCase,
    private val getSkiPlaceDetailsUseCase: GetSkiPlaceDetailsUseCase,
    private val saveSkiPlacesUseCase: SaveSkiPlaceUseCase,
    private val upsertUseCase: UpsertUseCase,
    private val reloadSkiPlacesUseCase: ReloadSkiPlacesUseCase
) : AndroidViewModel(app) {

    private val _skiPlacesListLoaded: MutableLiveData<List<SkiPlace>> =
        MutableLiveData<List<SkiPlace>>()
    val skiPlacesListLoaded: LiveData<List<SkiPlace>> get() = _skiPlacesListLoaded

    private val _skiPlacesFilteredListLoaded: MutableLiveData<List<SkiPlace>> =
        MutableLiveData<List<SkiPlace>>()
    val skiPlacesFilteredListLoaded: LiveData<List<SkiPlace>> get() = _skiPlacesFilteredListLoaded

    private val _skiSavedPlacesListLoaded: MutableLiveData<List<SkiPlace>> =
        MutableLiveData<List<SkiPlace>>()
    val skiSavedPlacesListLoaded: LiveData<List<SkiPlace>> get() = _skiSavedPlacesListLoaded

    private val _skiPlaceDetailLoaded: MutableLiveData<SkiPlace> = MutableLiveData<SkiPlace>()
    val skiPlaceDetailLoaded: LiveData<SkiPlace> get() = _skiPlaceDetailLoaded

    fun getSkiPlaceDetailsById(skiPlaceId: String) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            val skiPlaceDetails = getSkiPlaceDetailsUseCase.execute(skiPlaceId)
            withContext(Dispatchers.Main) {
                _skiPlaceDetailLoaded.value = skiPlaceDetails
            }
        }
    }

    fun saveSkiPlace(skiPlace: SkiPlace) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            saveSkiPlacesUseCase.execute(skiPlace)
        }
    }

    fun getSkiPlaces(filterString: String) {
        if (filterString.isEmpty()) {
            getAllSkiPlaces()
        } else {
            getFilteredSkiPlaces(filterString = filterString)
        }
    }

    fun reloadSkiPlacesList() = viewModelScope.launch {
        try {
            if (Util.hasInternetConnection(getApplication())) {
                Toast.makeText(getApplication(), Constants.SKI_PLACES_RELOAD, Toast.LENGTH_SHORT)
                    .show()
                withContext(Dispatchers.IO) {
                    reloadSkiPlacesUseCase.execute()
                }
            } else {
                Toast.makeText(getApplication(), NO_INTERNET, Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getAllSkiPlacesSaved() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            val skiPlacesSaved = getSavedSkiPlacesUseCase.execute()
            withContext(Dispatchers.Main) {
                _skiSavedPlacesListLoaded.value = skiPlacesSaved
            }
        }
    }

    fun deleteSkiPlace(skiPlace: SkiPlace) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            deleteSkiPlaceUseCase.execute(skiPlace)
        }
    }

    fun saveSkiPlaceSaved(skiPlace: SkiPlace) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            upsertUseCase.execute(skiPlace)
        }
    }

    fun saveCurrentSkiPlace() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            val skiPlaceSaved = _skiPlaceDetailLoaded.value
            skiPlaceSaved?.let { saveSkiPlacesUseCase.execute(skiPlaceSaved) }
        }
    }

    private fun getFilteredSkiPlaces(filterString: String) = viewModelScope.launch {
        val currentSkiPlacesList = skiPlacesListLoaded.value ?: emptyList()
        val skiPlaces = getSkiPlacesUseCase.execute(filterString, currentSkiPlacesList)
        withContext(Dispatchers.Main) {
            _skiPlacesFilteredListLoaded.value = skiPlaces
        }
    }

    private fun getAllSkiPlaces() = viewModelScope.launch {
        try {
            val skiPlaces = getSkiPlacesUseCase.execute()
            withContext(Dispatchers.Main) {
                _skiPlacesListLoaded.value = skiPlaces
            }
            if (!Util.hasInternetConnection(getApplication())) {
                Toast.makeText(getApplication(), NO_INTERNET, Toast.LENGTH_SHORT).show()
                Toast.makeText(getApplication(), LOAD_FROM_LOCAL_DATABASE, Toast.LENGTH_SHORT)
                    .show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
