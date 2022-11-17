package com.epicteam1.skimountains.feature_weather.data.repository

import com.epicteam1.skimountains.feature_ski_places.core.Constants.UNKNOWN_ERROR
import com.epicteam1.skimountains.feature_ski_places.domain.util.Resource
import com.epicteam1.skimountains.feature_weather.data.mappers.toWeatherInfo
import com.epicteam1.skimountains.feature_weather.data.remote.WeatherApi
import com.epicteam1.skimountains.feature_weather.domain.repository.WeatherRepository
import com.epicteam1.skimountains.feature_weather.domain.weather.WeatherInfo
import java.lang.Exception
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    lat = lat,
                    long = long
                ).toWeatherInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: UNKNOWN_ERROR)
        }
    }
}