package com.epicteam1.skimountains.feature_ski_places.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.epicteam1.skimountains.SkiApp
import com.epicteam1.skimountains.feature_ski_places.domain.repository.SkiPlaceRepository

class SkiViewModelProviderFactory(
    val app: SkiApp,
    val skiPlaceRepository: SkiPlaceRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SkiViewModel(app, skiPlaceRepository) as T
    }
}