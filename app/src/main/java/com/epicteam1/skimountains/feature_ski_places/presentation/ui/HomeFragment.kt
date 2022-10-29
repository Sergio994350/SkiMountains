package com.epicteam1.skimountains.feature_ski_places.presentation.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.epicteam1.skimountains.R
import com.epicteam1.skimountains.databinding.FragmentHomeBinding
import com.epicteam1.skimountains.feature_ski_places.core.Constants.DETAILS
import com.epicteam1.skimountains.feature_ski_places.core.Constants.SKI_PLACE_SAVED
import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace
import com.epicteam1.skimountains.feature_ski_places.presentation.adapter.SkiPlacesAdapter
import com.epicteam1.skimountains.feature_ski_places.presentation.ui.SplashFragment.Companion.startCount
import com.epicteam1.skimountains.feature_ski_places.presentation.viewModel.SkiPlaceViewModel
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var skiPlacesAdapter: SkiPlacesAdapter
    private val skiPlaceViewModel by viewModel<SkiPlaceViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        initDatabase()
        setObservers()

    }

    private fun setObservers() {
        skiPlaceViewModel.skiPlacesListLoaded.observe(viewLifecycleOwner, ::updateSkiPlacesList)
    }

    private fun updateSkiPlacesList(skiPlaces: List<SkiPlace>) {
        skiPlacesAdapter.differ.submitList(skiPlaces)
    }

    private fun onSkiPlaceClick(skiPlace: SkiPlace) {
        val bundle = Bundle().apply {
            putSerializable(DETAILS, skiPlace.skiPlaceId)
        }
        findNavController().navigate(R.id.action_home2_to_details, bundle)
    }

    private fun onSkiPlaceSaveClick(skiPlace: SkiPlace) {
        skiPlaceViewModel.saveSkiPlace(skiPlace)
        view?.let {
            Snackbar.make(it, SKI_PLACE_SAVED, Snackbar.LENGTH_SHORT).apply {
                animationMode = BaseTransientBottomBar.ANIMATION_MODE_SLIDE
                setBackgroundTint(Color.DKGRAY)
                setTextColor(Color.WHITE)
                show()
            }
        }
    }

    private fun setAdapter() {
        skiPlacesAdapter = SkiPlacesAdapter()
        skiPlacesAdapter.setOnItemClickListener { skiPlace -> onSkiPlaceClick(skiPlace) }
        skiPlacesAdapter.setOnItemSaveClickListener { skiPlace -> onSkiPlaceSaveClick(skiPlace) }
        binding.rvSkiPlaces.adapter = skiPlacesAdapter
        binding.rvSkiPlaces.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun initDatabase() {
        startCount++
        skiPlaceViewModel.getAllSkiPlacesFb()
    }
}