package com.epicteam1.skimountains.feature_weather.presentation

import com.epicteam1.skimountains.feature_weather.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)