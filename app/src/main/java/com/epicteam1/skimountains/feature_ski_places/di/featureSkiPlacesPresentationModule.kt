package com.epicteam1.skimountains.feature_ski_places.di

import com.epicteam1.skimountains.feature_ski_places.presentation.viewModel.SkiPlaceViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureSkiPlacesPresentationModule = module {

    viewModel {
        SkiPlaceViewModel(
            app = androidApplication(),
            deleteFavouriteSkiPlaceUseCase = get(),
            getSkiPlacesUseCase = get(),
            getFavouriteSkiPlacesUseCase = get(),
            getSkiPlaceDetailsUseCase = get(),
            addFavouriteSkiPlaceUseCase = get(),
            upsertUseCase = get(),
            reloadSkiPlacesUseCase = get(),
        )
    }
}