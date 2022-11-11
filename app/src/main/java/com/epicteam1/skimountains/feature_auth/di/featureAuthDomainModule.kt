package com.epicteam1.skimountains.feature_auth.di

import com.epicteam1.skimountains.feature_auth.data.repository.AuthRepositoryImpl
import com.epicteam1.skimountains.feature_auth.domain.repository.AuthRepository
import com.epicteam1.skimountains.feature_auth.domain.usecases.*
import org.koin.dsl.module

val featureAuthDomainModule = module {

    single<AuthRepository> { AuthRepositoryImpl(authenticator = get()) }

    factory { GetCurrentUserUseCase(authRepository = get()) }
    factory { SendResetPasswordUseCase(authRepository = get()) }
    factory { SignInWithEmailPasswordUseCase(authRepository = get()) }
    factory { SignUpWithEmailPasswordUseCase(authRepository = get()) }
    factory { SignOutUseCase(authRepository = get()) }
}