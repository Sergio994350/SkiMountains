package com.epicteam1.skimountains.feature_weather.data.remote

import com.epicteam1.skimountains.feature_ski_places.core.Constants.BASE_URL_WEATHER
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET(BASE_URL_WEATHER)
    suspend fun getWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double
    ): WeatherDto
}