package com.epicteam1.skimountains.feature_ski_places.di

import com.epicteam1.skimountains.feature_ski_places.domain.usecases.DeleteFavouriteSkiPlaceUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.GetSkiPlacesUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.GetFavouriteSkiPlacesUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.GetSkiPlaceDetailsUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.ReloadSkiPlacesUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.AddFavouriteSkiPlaceUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.UpsertUseCase
import org.koin.dsl.module

val featureSkiPlacesDomainModule = module {

    factory { DeleteFavouriteSkiPlaceUseCase(skiPlaceRepository = get()) }
    factory { GetSkiPlacesUseCase(skiPlaceRepository = get()) }
    factory { GetFavouriteSkiPlacesUseCase(skiPlaceRepository = get()) }
    factory { GetSkiPlaceDetailsUseCase(skiPlaceRepository = get()) }
    factory { AddFavouriteSkiPlaceUseCase(skiPlaceRepository = get()) }
    factory { UpsertUseCase(skiPlaceRepository = get()) }
    factory { ReloadSkiPlacesUseCase(skiPlaceRepository = get()) }
}