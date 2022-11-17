package com.epicteam1.skimountains.feature_weather.domain.repository

import com.epicteam1.skimountains.feature_ski_places.domain.util.Resource
import com.epicteam1.skimountains.feature_weather.domain.weather.WeatherInfo


interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}