package com.epicteam1.skimountains.feature_weather.data.dto

import com.squareup.moshi.Json

data class WeatherDataDto(
    val time: List<String>,

    @field:Json(name = "temperature_2m")
    val temperatures: List<Double>,

    @field:Json(name = "weathercode")
    val weatherCodes: List<Int>,
)
