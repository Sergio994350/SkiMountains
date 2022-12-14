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
import com.epicteam1.skimountains.feature_ski_places.core.EMPTY
import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace
import com.epicteam1.skimountains.feature_ski_places.presentation.adapter.SkiPlacesAdapter
import com.epicteam1.skimountains.feature_ski_places.presentation.viewModel.SkiPlaceViewModel
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var skiPlacesAdapter: SkiPlacesAdapter
    private val skiPlaceViewModel by viewModel<SkiPlaceViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        setObservers()
        setFragmentListeners()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        val filterString: String = binding.searchSkiPlace.editText?.text.toString()
        loadSkiPlacesList(filterString = filterString)
    }

    private fun setObservers() {
        skiPlaceViewModel.skiPlacesListLoaded.observe(viewLifecycleOwner, ::updateSkiPlacesList)
    }

    private fun updateSkiPlacesList(skiPlaces: List<SkiPlace>) {
        skiPlacesAdapter.submitSkiPlacesList(skiPlaces)
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
        skiPlacesAdapter = SkiPlacesAdapter(
            { skiPlace -> onSkiPlaceClick(skiPlace = skiPlace) },
            { skiPlace -> onSkiPlaceSaveClick(skiPlace = skiPlace) })
        with(binding) {
            rvSkiPlaces.adapter = skiPlacesAdapter
            rvSkiPlaces.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setFragmentListeners() {
        with(binding) {
            ivRefreshHome.setOnClickListener {
                skiPlaceViewModel.reloadSkiPlacesList()
                binding.searchSkiPlace.editText?.text?.clear()
                loadSkiPlacesList()
            }
            ivSortHome.setOnClickListener {
                skiPlaceViewModel.sortSkiPlaceList()
            }
            searchSkiPlace.setEndIconOnClickListener {
                val filterString: String = binding.searchSkiPlace.editText?.text.toString()
                loadSkiPlacesList(filterString = filterString)
            }
        }
    }

    private fun loadSkiPlacesList(filterString: String = String.EMPTY) {
        skiPlaceViewModel.getSkiPlaces(filterString = filterString)
    }
}