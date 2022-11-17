package com.epicteam1.skimountains.feature_weather.domain.repository

import com.epicteam1.skimountains.feature_weather.domain.models.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(latitude: Double, longitude: Double): WeatherInfo
}