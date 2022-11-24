package com.epicteam1.skimountains.feature_ski_places.di

import com.epicteam1.skimountains.di.ioDispatcherName
import com.epicteam1.skimountains.di.mainDispatcherName
import com.epicteam1.skimountains.feature_ski_places.presentation.viewModel.SkiPlaceViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val featureSkiPlacesPresentationModule = module {

    viewModel {
        SkiPlaceViewModel(
            app = androidApplication(),
            deleteSkiPlaceUseCase = get(),
            getSkiPlacesUseCase = get(),
            getSavedSkiPlacesUseCase = get(),
            getSkiPlaceDetailsUseCase = get(),
            saveSkiPlacesUseCase = get(),
            reloadSkiPlacesUseCase = get(),
            getWeatherUseCase = get(),
            sortSkiPlaceListUseCase = get(),
            ioDispatcher = get(named(ioDispatcherName)),
            mainDispatcher = get(named(mainDispatcherName))
        )
    }
}