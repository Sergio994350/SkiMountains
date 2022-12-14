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
import com.epicteam1.skimountains.feature_auth.Util
import com.epicteam1.skimountains.feature_ski_places.core.Constants.SORT_LIST
import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.DeleteSkiPlaceUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.GetSavedSkiPlacesUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.GetSkiPlaceDetailsUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.GetSkiPlacesUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.ReloadSkiPlacesUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.SaveSkiPlaceUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.SortSkiPlaceListUseCase
import com.epicteam1.skimountains.feature_weather.domain.models.WeatherData
import com.epicteam1.skimountains.feature_weather.domain.usecases.GetWeatherUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SkiPlaceViewModel(
    app: Application,
    private val deleteSkiPlaceUseCase: DeleteSkiPlaceUseCase,
    private val getSkiPlacesUseCase: GetSkiPlacesUseCase,
    private val getSavedSkiPlacesUseCase: GetSavedSkiPlacesUseCase,
    private val getSkiPlaceDetailsUseCase: GetSkiPlaceDetailsUseCase,
    private val saveSkiPlacesUseCase: SaveSkiPlaceUseCase,
    private val reloadSkiPlacesUseCase: ReloadSkiPlacesUseCase,
    private val getWeatherUseCase: GetWeatherUseCase,
    private val sortSkiPlaceListUseCase: SortSkiPlaceListUseCase,
    private val ioDispatcher: CoroutineDispatcher,
    private val mainDispatcher: MainCoroutineDispatcher,
) : AndroidViewModel(app) {

    private val _skiPlacesListLoaded: MutableLiveData<List<SkiPlace>> =
        MutableLiveData<List<SkiPlace>>()
    val skiPlacesListLoaded: LiveData<List<SkiPlace>> get() = _skiPlacesListLoaded

    private val _skiSavedPlacesListLoaded: MutableLiveData<List<SkiPlace>> =
        MutableLiveData<List<SkiPlace>>()
    val skiSavedPlacesListLoaded: LiveData<List<SkiPlace>> get() = _skiSavedPlacesListLoaded

    private val _skiPlaceDetailLoaded: MutableLiveData<SkiPlace> = MutableLiveData<SkiPlace>()
    val skiPlaceDetailLoaded: LiveData<SkiPlace> get() = _skiPlaceDetailLoaded

    private val _skiPlaceWeatherLoaded: MutableLiveData<WeatherData> =
        MutableLiveData<WeatherData>()
    val skiPlaceWeatherLoaded: LiveData<WeatherData> get() = _skiPlaceWeatherLoaded

    private var sortOrderAsc = true

    fun getSkiPlaceDetailsById(skiPlaceId: String) = viewModelScope.launch {
        try {
            if (Util.hasInternetConnection(getApplication())) {
                withContext(ioDispatcher) {
                    val skiPlaceDetails = getSkiPlaceDetailsUseCase.execute(skiPlaceId)
                    val skiPlaceWeather = getWeatherUseCase.execute(
                        skiPlaceDetails.latitude,
                        skiPlaceDetails.longitude
                    )
                    withContext(mainDispatcher) {
                        _skiPlaceDetailLoaded.value = skiPlaceDetails
                        _skiPlaceWeatherLoaded.value = skiPlaceWeather?.let { skiPlaceWeather }
                    }
                }
            } else {
                Toast.makeText(getApplication(), NO_INTERNET, Toast.LENGTH_SHORT).show()
                withContext(ioDispatcher) {
                    val skiPlaceDetails = getSkiPlaceDetailsUseCase.execute(skiPlaceId)
                    withContext(mainDispatcher) {
                        _skiPlaceDetailLoaded.value = skiPlaceDetails
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun saveSkiPlace(skiPlace: SkiPlace) = viewModelScope.launch {
        withContext(ioDispatcher) {
            saveSkiPlacesUseCase.execute(skiPlace)
        }
    }

    fun getSkiPlaces(filterString: String) = viewModelScope.launch {
        try {
            val skiPlaces = getSkiPlacesUseCase.execute(filterString = filterString)
            withContext(mainDispatcher) {
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

    fun sortSkiPlaceList() {
        sortOrderAsc = !sortOrderAsc
        val sortedSkiPlaceList = sortSkiPlaceListUseCase.execute(_skiPlacesListLoaded.value, sortOrderAsc)
        _skiPlacesListLoaded.value = sortedSkiPlaceList
        Toast.makeText(getApplication(), SORT_LIST, Toast.LENGTH_SHORT).show()
    }

    fun reloadSkiPlacesList() = viewModelScope.launch {
        try {
            if (Util.hasInternetConnection(getApplication())) {
                Toast.makeText(getApplication(), Constants.SKI_PLACES_RELOAD, Toast.LENGTH_SHORT)
                    .show()
                withContext(ioDispatcher) {
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
        withContext(ioDispatcher) {
            val skiPlacesSaved = getSavedSkiPlacesUseCase.execute()
            withContext(mainDispatcher) {
                _skiSavedPlacesListLoaded.value = skiPlacesSaved
            }
        }
    }

    fun deleteSkiPlace(skiPlace: SkiPlace) = viewModelScope.launch {
        withContext(ioDispatcher) {
            deleteSkiPlaceUseCase.execute(skiPlace)
        }
    }

    fun saveCurrentSkiPlace() = viewModelScope.launch {
        withContext(ioDispatcher) {
            val skiPlaceSaved = _skiPlaceDetailLoaded.value
            skiPlaceSaved?.let { saveSkiPlacesUseCase.execute(skiPlaceSaved) }
        }
    }
}
