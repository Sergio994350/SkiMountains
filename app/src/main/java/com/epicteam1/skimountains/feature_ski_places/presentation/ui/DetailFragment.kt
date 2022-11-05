package com.epicteam1.skimountains.feature_ski_places.presentation.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.epicteam1.skimountains.R
import com.epicteam1.skimountains.databinding.FragmentDetailBinding
import com.epicteam1.skimountains.feature_ski_places.core.Constants
import com.epicteam1.skimountains.feature_ski_places.core.Constants.SKI_PLACE_SAVED
import com.epicteam1.skimountains.feature_ski_places.core.getDescriptionDataRus
import com.epicteam1.skimountains.feature_ski_places.core.getGeoDataRus
import com.epicteam1.skimountains.feature_ski_places.core.getTechnicalDataRus
import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace
import com.epicteam1.skimountains.feature_ski_places.presentation.viewModel.SkiPlaceViewModel
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val skiPlaceViewModel by viewModel<SkiPlaceViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val skiPlaceArg: String = arguments?.getString(Constants.DETAILS)!!

        setObservers()
        setOnClickListeners(view)
        skiPlaceViewModel.getSkiPlaceDetailsById(skiPlaceArg)

    }

    private fun setObservers() {
        skiPlaceViewModel.skiPlaceDetailLoaded.observe(viewLifecycleOwner, ::setSkiPlaceData)
    }

    private fun setOnClickListeners(view: View) {
        card_view_back.setOnClickListener {
            findNavController().apply { popBackStack() }
        }

        btn_how_to_get_details.setOnClickListener {
            findNavController().navigate(R.id.action_details_to_how_to_get_fragment)
        }

        card_view_save_ski_place_details.setOnClickListener {
            Snackbar.make(view, SKI_PLACE_SAVED, Snackbar.LENGTH_SHORT).apply {
                animationMode = BaseTransientBottomBar.ANIMATION_MODE_SLIDE
                setBackgroundTint(Color.DKGRAY)
                setTextColor(Color.WHITE)
                show()
            }
        }
    }

    private fun setSkiPlaceData(skiPlace: SkiPlace) {
        Glide.with(this).load(skiPlace.mainPic).into(binding.imageSkiPlaceBigDetails)
        binding.nameSkiPlaceDetails.text = skiPlace.nameRus
        binding.regionCategoryDetails.text = skiPlace.regionRus
        binding.technicalDataDetails.text = context?.let { skiPlace.getTechnicalDataRus(it) }
        binding.descriptionDataDetails.text = context?.let { skiPlace.getDescriptionDataRus(it) }
        binding.geoDataDetails.text = context?.let { skiPlace.getGeoDataRus(it) }
        btn_play_youtube_details.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(skiPlace.youTubeLink)))
        }
        btn_web_cite_details.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(skiPlace.webCite)))
        }
        btn_web_camera_details.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(skiPlace.webCamera)))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}