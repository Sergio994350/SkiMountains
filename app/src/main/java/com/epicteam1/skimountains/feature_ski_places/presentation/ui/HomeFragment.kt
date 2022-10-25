package com.epicteam1.skimountains.feature_ski_places.presentation.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.epicteam1.skimountains.MainActivity
import com.epicteam1.skimountains.R
import com.epicteam1.skimountains.feature_ski_places.domain.util.Constants.DETAILS
import com.epicteam1.skimountains.feature_ski_places.domain.util.Constants.SKI_PLACE_SAVED
import com.epicteam1.skimountains.feature_ski_places.presentation.adapter.SkiPlacesAdapter
import com.epicteam1.skimountains.feature_ski_places.presentation.ui.SplashFragment.Companion.startCount
import com.epicteam1.skimountains.feature_ski_places.presentation.viewModel.SkiViewModel
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var viewModel: SkiViewModel
    lateinit var skiPlacesAdapter: SkiPlacesAdapter

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        setupRecyclerViewMain()

        if (startCount == 1) {
            initDatabase()            
        }

        viewModel.getAllSkiPlaces().observe(viewLifecycleOwner, Observer { it ->
            skiPlacesAdapter.differ.submitList(it)
        })

        skiPlacesAdapter.setOnItemClickListener { Place ->
            val bundle = Bundle().apply {
                putSerializable(DETAILS, Place)
            }
            findNavController().navigate(R.id.action_home2_to_details, bundle)
        }

        skiPlacesAdapter.setOnItemClickListener2 {
            viewModel.saveSkiPlace(it)
            Snackbar.make(view, SKI_PLACE_SAVED, Snackbar.LENGTH_SHORT).apply {
                animationMode = BaseTransientBottomBar.ANIMATION_MODE_SLIDE
                setBackgroundTint(Color.DKGRAY)
                setTextColor(Color.WHITE)
                show()
            }
        }
    }


    private fun setupRecyclerViewMain() {
        skiPlacesAdapter = SkiPlacesAdapter()
        rv_ski_places.apply {
            adapter = skiPlacesAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun initDatabase() {
        startCount++
        viewModel.getAllSkiPlacesFb()
    }
}