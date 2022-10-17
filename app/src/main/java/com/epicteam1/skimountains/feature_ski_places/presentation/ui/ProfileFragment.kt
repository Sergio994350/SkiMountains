package com.epicteam1.skimountains.feature_ski_places.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.epicteam1.skimountains.MainActivity
import com.epicteam1.skimountains.R
import com.epicteam1.skimountains.feature_ski_places.presentation.viewModel.SkiViewModel

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    lateinit var viewModel: SkiViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        // TODO
    }
}