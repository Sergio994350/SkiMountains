package com.epicteam1.skimountains.feature_ski_places.di

import com.epicteam1.skimountains.feature_ski_places.domain.usecases.DeleteSkiPlaceUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.GetAllSkiPlacesUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.GetSavedSkiPlacesUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.GetFilteredSkiPlacesUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.GetSkiPlaceDetailsUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.ReloadSkiPlacesUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.SaveSkiPlaceUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.UpsertUseCase
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.implementations.DeleteSkiPlaceUseCaseImpl
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.implementations.GetAllSkiPlacesUseCaseImpl
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.implementations.GetSavedSkiPlacesUseCaseImpl
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.implementations.GetFilteredSkiPlacesUseCaseImpl
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.implementations.GetSkiPlaceDetailsUseCaseImpl
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.implementations.ReloadSkiPlacesUseCaseImpl
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.implementations.SaveSkiPlaceUseCaseImpl
import com.epicteam1.skimountains.feature_ski_places.domain.usecases.implementations.UpsertUseCaseImpl
import org.koin.dsl.module

val featureSkiPlacesDomainModule = module {

    factory<DeleteSkiPlaceUseCase> { DeleteSkiPlaceUseCaseImpl(skiPlaceRepository = get()) }
    factory<GetAllSkiPlacesUseCase> { GetAllSkiPlacesUseCaseImpl(skiPlaceRepository = get()) }
    factory<GetSavedSkiPlacesUseCase> { GetSavedSkiPlacesUseCaseImpl(skiPlaceRepository = get()) }
    factory<GetFilteredSkiPlacesUseCase> { GetFilteredSkiPlacesUseCaseImpl(skiPlaceRepository = get()) }
    factory<GetSkiPlaceDetailsUseCase> { GetSkiPlaceDetailsUseCaseImpl(skiPlaceRepository = get()) }
    factory<SaveSkiPlaceUseCase> { SaveSkiPlaceUseCaseImpl(skiPlaceRepository = get()) }
    factory<UpsertUseCase> { UpsertUseCaseImpl(skiPlaceRepository = get()) }
    factory<ReloadSkiPlacesUseCase> { ReloadSkiPlacesUseCaseImpl(skiPlaceRepository = get()) }
}