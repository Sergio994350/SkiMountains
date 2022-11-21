package com.epicteam1.skimountains.feature_ski_places.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.epicteam1.skimountains.R
import com.epicteam1.skimountains.feature_ski_places.core.Constants.SPLASH_DELAY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment(R.layout.fragment_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {
            delay(SPLASH_DELAY)
            findNavController().navigate(R.id.action_splash_to_home_fragment)
        }
    }
}