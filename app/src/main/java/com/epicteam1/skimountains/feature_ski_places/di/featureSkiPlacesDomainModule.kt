package com.epicteam1.skimountains.feature_ski_places.di

import com.epicteam1.skimountains.feature_ski_places.domain.usecases.DeleteSkiPlaceUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.GetSkiPlacesUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.GetSavedSkiPlacesUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.GetSkiPlaceDetailsUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.ReloadSkiPlacesUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.SaveSkiPlaceUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.SortSkiPlaceListUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.UpsertUseCase
import org.koin.dsl.module

val featureSkiPlacesDomainModule = module {

    factory { DeleteSkiPlaceUseCase(skiPlaceRepository = get()) }
    factory { GetSkiPlacesUseCase(skiPlaceRepository = get()) }
    factory { GetSavedSkiPlacesUseCase(skiPlaceRepository = get()) }
    factory { GetSkiPlaceDetailsUseCase(skiPlaceRepository = get()) }
    factory { SaveSkiPlaceUseCase(skiPlaceRepository = get()) }
    factory { UpsertUseCase(skiPlaceRepository = get()) }
    factory { ReloadSkiPlacesUseCase(skiPlaceRepository = get()) }
    factory { SortSkiPlaceListUseCase() }
}