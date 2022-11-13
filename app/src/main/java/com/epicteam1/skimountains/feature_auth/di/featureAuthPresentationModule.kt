package com.epicteam1.skimountains.feature_auth.di

import com.epicteam1.skimountains.feature_auth.presentation.viewModel.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureAuthPresentationModule = module {

    viewModel {
        AuthViewModel(
            signInWithEmailPasswordUseCase = get(),
            signUpWithEmailPasswordUseCase = get(),
            getCurrentUserUseCase = get(),
            signOutUseCase = get(),
            sendResetPasswordUseCase = get()
        )
    }
}