package com.epicteam1.skimountains.feature_ski_places.presentation.viewModel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.epicteam1.skimountains.SkiApp
import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.DeleteFavouriteSkiPlaceUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.GetSkiPlacesUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.GetFavouriteSkiPlacesUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.GetSkiPlaceDetailsUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.ReloadSkiPlacesUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.AddFavouriteSkiPlaceUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.UpsertUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SkiPlaceViewModel(
    app: Application,
    private val deleteFavouriteSkiPlaceUseCase: DeleteFavouriteSkiPlaceUseCase,
    private val getSkiPlacesUseCase: GetSkiPlacesUseCase,
    private val getFavouriteSkiPlacesUseCase: GetFavouriteSkiPlacesUseCase,
    private val getSkiPlaceDetailsUseCase: GetSkiPlaceDetailsUseCase,
    private val addFavouriteSkiPlaceUseCase: AddFavouriteSkiPlaceUseCase,
    private val upsertUseCase: UpsertUseCase,
    private val reloadSkiPlacesUseCase: ReloadSkiPlacesUseCase
) : AndroidViewModel(app) {

    private val _skiPlacesListLoaded: MutableLiveData<List<SkiPlace>> = MutableLiveData<List<SkiPlace>>()
    val skiPlacesListLoaded: LiveData<List<SkiPlace>> get() = _skiPlacesListLoaded

    private val _skiPlacesFilteredListLoaded: MutableLiveData<List<SkiPlace>> = MutableLiveData<List<SkiPlace>>()
    val skiPlacesFilteredListLoaded: LiveData<List<SkiPlace>> get() = _skiPlacesFilteredListLoaded

    private val _skiFavouritePlacesListLoaded: MutableLiveData<List<SkiPlace>> = MutableLiveData<List<SkiPlace>>()
    val skiFavouritePlacesListLoaded: LiveData<List<SkiPlace>> get() = _skiFavouritePlacesListLoaded

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

    fun addFavouriteSkiPlace(skiPlace: SkiPlace) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            addFavouriteSkiPlaceUseCase.execute(skiPlace)
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
        withContext(Dispatchers.IO) {
            reloadSkiPlacesUseCase.execute()
        }
    }

    fun getFavouriteSkiPlaces() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            val skiPlacesSaved = getFavouriteSkiPlacesUseCase.execute()
            withContext(Dispatchers.Main) {
                _skiFavouritePlacesListLoaded.value = skiPlacesSaved
            }
        }
    }

    fun deleteFavouriteSkiPlace(skiPlace: SkiPlace) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            deleteFavouriteSkiPlaceUseCase.execute(skiPlace)
        }
    }

    fun saveSkiPlaceSaved(skiPlace: SkiPlace) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            upsertUseCase.execute(skiPlace)
        }
    }

    fun addCurrentSkiPlaceToFavourites() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            val currentSkiPlace = _skiPlaceDetailLoaded.value
            currentSkiPlace?.let { addFavouriteSkiPlaceUseCase.execute(currentSkiPlace) }
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
        val skiPlaces = getSkiPlacesUseCase.execute()
        withContext(Dispatchers.Main) {
            _skiPlacesListLoaded.value = skiPlaces
        }
    }

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
