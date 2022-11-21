package com.epicteam1.skimountains.feature_weather.data.remote

import com.epicteam1.skimountains.feature_weather.core.WeatherConstants.FORECAST_URL_WEATHER
import com.epicteam1.skimountains.feature_weather.data.dto.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET(FORECAST_URL_WEATHER)
    suspend fun getWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double
    ): WeatherDto
}