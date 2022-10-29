package com.epicteam1.skimountains.feature_ski_places.presentation.viewModel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.epicteam1.skimountains.SkiApp
import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.DeleteSkiPlaceUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.GetAllSkiPlacesUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.GetInitAllSkiPlacesFirebaseUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.GetSavedSkiPlacesUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.GetSearchFirebaseUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.GetSkiPlaceDetailsUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.SaveSkiPlaceUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.SkiPlacesUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.UpsertUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SkiPlaceViewModel(
    app: Application,
    private val deleteSkiPlaceUseCase: DeleteSkiPlaceUseCase,
    private val getAllSkiPlacesUseCase: GetAllSkiPlacesUseCase,
    private val getInitAllSkiPlacesFirebaseUseCase: GetInitAllSkiPlacesFirebaseUseCase,
    private val getSavedSkiPlacesUseCase: GetSavedSkiPlacesUseCase,
    private val getSearchFirebaseUseCase: GetSearchFirebaseUseCase,
    private val getSkiPlaceDetailsUseCase: GetSkiPlaceDetailsUseCase,
    private val saveSkiPlacesUseCase: SaveSkiPlaceUseCase,
    private val upsertUseCase: UpsertUseCase
) : AndroidViewModel(app) {

    private val _skiPlacesListLoaded: MutableLiveData<List<SkiPlace>> = MutableLiveData<List<SkiPlace>>()
    val skiPlacesListLoaded: LiveData<List<SkiPlace>> get() = _skiPlacesListLoaded

    private val _skiSavedPlacesListLoaded: MutableLiveData<List<SkiPlace>> = MutableLiveData<List<SkiPlace>>()
    val skiSavedPlacesListLoaded: LiveData<List<SkiPlace>> get() = _skiSavedPlacesListLoaded

    private val _skiPlaceDetailLoaded: MutableLiveData<SkiPlace> = MutableLiveData<SkiPlace>()
    val skiPlaceDetailLoaded: LiveData<SkiPlace> get() = _skiPlaceDetailLoaded

    // search from home fragment - TODO
    fun getSearchFb(search: String) = viewModelScope.launch {
        getSearchFirebaseUseCase.execute(search)
    }

    fun getAllSkiPlacesFb() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getInitAllSkiPlacesFirebaseUseCase.execute()
                getSkiPlaces()
            }
        }
    }

    // detail fragment - TODO
    fun getSkiPlaceDetailsById(skiPlaceId: String) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            val skiPlaceDetails = getSkiPlaceDetailsUseCase.execute(skiPlaceId)
            withContext(Dispatchers.Main) {
                _skiPlaceDetailLoaded.value = skiPlaceDetails
            }
        }
    }

    // save fragment
    fun saveSkiPlace(skiPlace: SkiPlace) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            saveSkiPlacesUseCase.execute(skiPlace)
        }
    }

    fun getAllSkiPlaces() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            getSkiPlaces()
        }
    }

    fun getAllSkiPlacesSaved() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            getSavedSkiPlacesUseCase.execute()
        }
    }

    fun deleteSkiPlace(skiPlace: SkiPlace) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            deleteSkiPlaceUseCase.execute(skiPlace)
        }
    }

    // save fragment - TODO
    fun saveSkiPlaceSaved(skiPlace: SkiPlace) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            upsertUseCase.execute(skiPlace)
        }
    }

    private suspend fun getSkiPlaces() {
        val skiPlaces = getAllSkiPlacesUseCase.execute()
        withContext(Dispatchers.Main) {
            _skiPlacesListLoaded.value = skiPlaces
        }
    }

    // function for checking internet connection for API>=23 and <23
    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<SkiApp>()
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }
}