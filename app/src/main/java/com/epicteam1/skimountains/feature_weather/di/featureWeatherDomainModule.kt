package com.epicteam1.skimountains.feature_weather.di

import com.epicteam1.skimountains.feature_weather.domain.usecases.GetWeatherUseCase
import org.koin.dsl.module

val featureWeatherDomainModule = module {

    factory { GetWeatherUseCase( weatherRepository = get()) }

}