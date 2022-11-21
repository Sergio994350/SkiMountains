package com.epicteam1.skimountains.feature_weather.di

import com.epicteam1.skimountains.feature_weather.core.WeatherConstants.BASE_URL_WEATHER
import com.epicteam1.skimountains.feature_weather.data.remote.RetrofitClient
import com.epicteam1.skimountains.feature_weather.data.remote.WeatherApi
import com.epicteam1.skimountains.feature_weather.data.repository.WeatherRepositoryImpl
import com.epicteam1.skimountains.feature_weather.domain.repository.WeatherRepository
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.converter.moshi.MoshiConverterFactory


val featureWeatherDataModule = module {

    single<Converter.Factory> { MoshiConverterFactory.create() }

    single<WeatherApi> { RetrofitClient(baseURL = BASE_URL_WEATHER, api = WeatherApi::class.java, converterFactory = get()).getClient() }

    single<WeatherRepository> { WeatherRepositoryImpl(weatherApi = get()) }
}