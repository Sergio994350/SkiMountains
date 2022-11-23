package com.epicteam1.skimountains

import android.app.Application
import android.content.Context
import com.epicteam1.skimountains.di.commonDispatchers
import com.epicteam1.skimountains.feature_auth.di.featureAuthDataModule
import com.epicteam1.skimountains.feature_auth.di.featureAuthDomainModule
import com.epicteam1.skimountains.feature_auth.di.featureAuthPresentationModule
import com.epicteam1.skimountains.feature_ski_places.di.featureSkiPlacesDataModule
import com.epicteam1.skimountains.feature_ski_places.di.featureSkiPlacesDomainModule
import com.epicteam1.skimountains.feature_ski_places.di.featureSkiPlacesPresentationModule
import com.epicteam1.skimountains.feature_weather.di.featureWeatherDataModule
import com.epicteam1.skimountains.feature_weather.di.featureWeatherDomainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SkiApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instanceApp = this

        startKoin {
            androidContext(this@SkiApp)
            modules(
                featureSkiPlacesDataModule,
                featureSkiPlacesDomainModule,
                featureSkiPlacesPresentationModule,
                featureAuthDataModule,
                featureAuthDomainModule,
                featureAuthPresentationModule,
                featureWeatherDataModule,
                featureWeatherDomainModule,
                commonDispatchers
            )
        }
    }

    companion object {
        lateinit var instanceApp: SkiApp
        fun getContext(): Context {
            return instanceApp.applicationContext
        }
    }
}