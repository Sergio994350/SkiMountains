package com.epicteam1.skimountains.feature_weather.core

import android.annotation.SuppressLint
import com.epicteam1.skimountains.feature_weather.data.dto.WeatherDataDto
import com.epicteam1.skimountains.feature_weather.data.dto.WeatherDto
import com.epicteam1.skimountains.feature_weather.domain.models.WeatherData
import com.epicteam1.skimountains.feature_weather.domain.models.WeatherInfo
import com.epicteam1.skimountains.feature_weather.domain.models.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)

@SuppressLint("NewApi")
fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        IndexedWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map { it.data }
    }
}

@SuppressLint("NewApi")
fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }
    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}