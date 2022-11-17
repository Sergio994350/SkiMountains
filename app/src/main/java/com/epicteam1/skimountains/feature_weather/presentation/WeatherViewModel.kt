package com.epicteam1.skimountains.feature_weather.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epicteam1.skimountains.feature_ski_places.core.Constants.LOCATION_ERROR
import com.epicteam1.skimountains.feature_ski_places.domain.util.Resource
import com.epicteam1.skimountains.feature_weather.domain.location.LocationTracker
import com.epicteam1.skimountains.feature_weather.domain.repository.WeatherRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
): ViewModel() {

    var state by mutableStateOf(WeatherState())
    private set

    fun loadWeatherInfo() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            locationTracker.getCurrentLocation()?.let { location ->
                when(val result = repository.getWeatherData(location.latitude, location.longitude)) {
                    is Resource.Success -> {
                        state = state.copy(
                            weatherInfo = result.data,
                            isLoading = false,
                            error = null
                        )
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            weatherInfo = null,
                            isLoading = false,
                            error = result.message
                        )
                    }
                    else -> {}
                }
            } ?: kotlin.run {
                state = state.copy(
                    isLoading = false,
                    error = LOCATION_ERROR
                )
            }
        }
    }
}