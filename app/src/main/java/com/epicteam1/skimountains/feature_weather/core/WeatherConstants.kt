package com.epicteam1.skimountains.feature_weather.core

object WeatherConstants {
    const val BASE_URL_WEATHER = "https://api.open-meteo.com/"
    const val FORECAST_URL_WEATHER =
        "v1/forecast?hourly=temperature_2m,weathercode"
    const val TODAY_WEATHER = "Сегодня"
    const val UNKNOWN_ERROR = "Неизвестная ошибка"
    const val LOCATION_ERROR = "Ошибка позиционирования, проверьте GPS"
    const val CELCIUS = "°C"
}