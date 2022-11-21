package com.epicteam1.skimountains.feature_weather.data.remote

import retrofit2.Converter.Factory
import retrofit2.Retrofit

class RetrofitClient<T>(private val baseURL:String, private val api: Class<T>, private val converterFactory: Factory) {

    fun getClient(): T = Retrofit.Builder().baseUrl(baseURL).addConverterFactory(converterFactory).build().create(api)
}