package com.epicteam1.skimountains.feature_auth.di

import com.epicteam1.skimountains.feature_auth.data.remote.FirebaseAuthDataSource
import com.epicteam1.skimountains.feature_auth.data.remote.FirebaseAuthDataSourceImpl

import org.koin.dsl.module

val featureAuthDataModule = module {

    single<FirebaseAuthDataSource> { FirebaseAuthDataSourceImpl() }

}