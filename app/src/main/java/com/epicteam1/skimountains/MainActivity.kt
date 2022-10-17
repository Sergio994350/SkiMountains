package com.epicteam1.skimountains

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.epicteam1.skimountains.feature_ski_places.data.local.SkiDatabase
import com.epicteam1.skimountains.feature_ski_places.domain.repository.SkiPlaceRepository
import com.epicteam1.skimountains.feature_ski_places.presentation.viewModel.SkiViewModel
import com.epicteam1.skimountains.feature_ski_places.presentation.viewModel.SkiViewModelProviderFactory
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    lateinit var viewModel: SkiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        val skiRepository = SkiPlaceRepository(SkiDatabase(this))
        val viewModelProviderFactory =
            SkiViewModelProviderFactory(application as SkiApp, skiRepository)

        navView.setupWithNavController(navController)

        viewModel = ViewModelProvider(this, viewModelProviderFactory)[SkiViewModel::class.java]

    }
}