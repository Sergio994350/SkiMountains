package com.epicteam1.skimountains.feature_weather.domain.usecases

import com.epicteam1.skimountains.feature_weather.domain.models.WeatherData
import com.epicteam1.skimountains.feature_weather.domain.repository.WeatherRepository

class GetWeatherUseCase(private val weatherRepository: WeatherRepository) {
    suspend fun execute(latitude: String, longitude: String): WeatherData? {
        return weatherRepository.getWeatherData(latitude = latitude.toDouble(), longitude = longitude.toDouble()).currentWeatherData }
}