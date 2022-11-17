package com.epicteam1.skimountains.feature_weather.data.repository

import com.epicteam1.skimountains.feature_weather.core.toWeatherInfo
import com.epicteam1.skimountains.feature_weather.data.remote.WeatherApi
import com.epicteam1.skimountains.feature_weather.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherApi: WeatherApi
) : WeatherRepository {
    override suspend fun getWeatherData(latitude: Double, longitude: Double) = weatherApi.getWeatherData(lat = latitude, long = longitude).toWeatherInfo()
}