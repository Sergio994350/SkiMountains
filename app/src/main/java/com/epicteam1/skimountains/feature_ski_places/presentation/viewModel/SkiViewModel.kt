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
import com.epicteam1.skimountains.feature_ski_places.domain.repository.SkiPlaceRepository
import kotlinx.coroutines.launch

class SkiViewModel(
    app: Application,
    val skiPlaceRepository: SkiPlaceRepository
) : AndroidViewModel(app) {

    val search: LiveData<List<SkiPlace>> = MutableLiveData()
    val details: LiveData<SkiPlace> = MutableLiveData()
    val filter: LiveData<List<SkiPlace>> = MutableLiveData()
    val TAG = "ski_place_firebase"

    // search from home fragment - TODO
    fun getSearchFb(search: String) = viewModelScope.launch {
        skiPlaceRepository.getSearchFirebase(search)
    }

    fun getAllSkiPlacesFb() {
        try {
            if (hasInternetConnection()) {
                skiPlaceRepository.getInitAllSkiPlacesFirebase()
                Log.d(TAG, "Success")
            } else {
                Log.d(TAG, "No internet connection!")
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }


    // detail fragment - TODO
    fun getDetails(id: String) = viewModelScope.launch {
        skiPlaceRepository.getSkiPlaceById(id)
    }

    // save fragment
    fun saveSkiPlace(skiPlace: SkiPlace) = viewModelScope.launch {
        skiPlaceRepository.upsert(skiPlace)
    }

    fun getAllSkiPlaces() = skiPlaceRepository.getAllSkiPlaces()

    fun deleteSkiPlace(skiPlace: SkiPlace) = viewModelScope.launch {
        skiPlaceRepository.deleteSkiPlace(skiPlace)
    }

    // save fragment - TODO
    fun saveSkiPlaceSaved(skiPlace: SkiPlace) = viewModelScope.launch {
        skiPlaceRepository.upsert(skiPlace)
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
