package com.epicteam1.skimountains

import android.app.Application
import com.epicteam1.skimountains.feature_ski_places.di.featureSkiPlacesDataModule
import com.epicteam1.skimountains.feature_ski_places.di.featureSkiPlacesDomainModule
import com.epicteam1.skimountains.feature_ski_places.di.featureSkiPlacesPresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SkiApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SkiApp)
            modules(featureSkiPlacesDataModule, featureSkiPlacesDomainModule, featureSkiPlacesPresentationModule)
        }
    }
}