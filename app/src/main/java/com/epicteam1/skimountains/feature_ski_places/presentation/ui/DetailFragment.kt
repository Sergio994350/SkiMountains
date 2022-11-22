package com.epicteam1.skimountains.feature_ski_places.presentation.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.epicteam1.skimountains.R
import com.epicteam1.skimountains.databinding.FragmentDetailBinding
import com.epicteam1.skimountains.feature_ski_places.core.Constants
import com.epicteam1.skimountains.feature_ski_places.core.Constants.HOW_TO_GET_ARGS
import com.epicteam1.skimountains.feature_ski_places.core.Constants.NO_WEB_CAMERAS
import com.epicteam1.skimountains.feature_ski_places.core.Constants.NO_WEB_CITE
import com.epicteam1.skimountains.feature_ski_places.core.Constants.NO_YOUTUBE_LINK
import com.epicteam1.skimountains.feature_ski_places.core.Constants.SKI_PLACE_SAVED
import com.epicteam1.skimountains.feature_ski_places.core.EMPTY
import com.epicteam1.skimountains.feature_ski_places.core.getDescriptionDataRus
import com.epicteam1.skimountains.feature_ski_places.core.getGeoDataRus
import com.epicteam1.skimountains.feature_ski_places.core.getTechnicalDataRus
import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace
import com.epicteam1.skimountains.feature_ski_places.presentation.model.SkiPlaceHowToGetArgs
import com.epicteam1.skimountains.feature_ski_places.presentation.viewModel.SkiPlaceViewModel
import com.epicteam1.skimountains.feature_weather.core.WeatherConstants.CELCIUS
import com.epicteam1.skimountains.feature_weather.domain.models.WeatherData
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val skiPlaceViewModel by viewModel<SkiPlaceViewModel>()
    private lateinit var howToGetArgs: SkiPlaceHowToGetArgs


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

        val skiPlaceArg: String? = arguments?.getString(Constants.DETAILS)
        setObservers()
        setOnClickListeners(view)
        skiPlaceArg?.let {
            skiPlaceViewModel.getSkiPlaceDetailsById(skiPlaceArg)
        }
    }

    private fun setObservers() {
        skiPlaceViewModel.skiPlaceDetailLoaded.observe(viewLifecycleOwner, ::setSkiPlaceData)
        skiPlaceViewModel.skiPlaceDetailLoaded.observe(viewLifecycleOwner, ::setHowToGetArgs)
        skiPlaceViewModel.skiPlaceWeatherLoaded.observe(viewLifecycleOwner, ::setWeather)
    }

    private fun setOnClickListeners(view: View) {
        binding.cardViewBack.setOnClickListener {
            findNavController().apply { popBackStack() }
        }

        binding.btnHowToGetDetails.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable(HOW_TO_GET_ARGS, howToGetArgs)
            }
            findNavController().navigate(R.id.action_details_to_how_to_get_fragment, bundle)
        }

        binding.cardViewSaveSkiPlaceDetails.setOnClickListener {
            skiPlaceViewModel.saveCurrentSkiPlace()
            Snackbar.make(view, SKI_PLACE_SAVED, Snackbar.LENGTH_SHORT).apply {
                animationMode = BaseTransientBottomBar.ANIMATION_MODE_SLIDE
                setBackgroundTint(Color.DKGRAY)
                setTextColor(Color.WHITE)
                show()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setWeather(weatherData: WeatherData) {
        binding.tvWeatherTempDetails.text = weatherData.temperatureCelsius.toString() + CELCIUS
        binding.imageViewWeatherDetails.setImageResource(weatherData.weatherType.iconRes)
    }

    private fun setSkiPlaceData(skiPlace: SkiPlace) {
        Glide.with(this).load(skiPlace.mainPic).into(binding.imageSkiPlaceBigDetails)
        binding.nameSkiPlaceDetails.text = skiPlace.nameRus
        binding.regionCategoryDetails.text = skiPlace.regionRus
        binding.tvRegionBigDetails.text = skiPlace.regionBig
        binding.technicalDataDetails.text = context?.let { skiPlace.getTechnicalDataRus(it) }
        binding.descriptionDataDetails.text = context?.let { skiPlace.getDescriptionDataRus(it) }
        binding.geoDataDetails.text = context?.let { skiPlace.getGeoDataRus(it) }
        binding.btnPlayYoutubeDetails.setOnClickListener {
            if (skiPlace.youTubeLink.isNotBlank()) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(skiPlace.youTubeLink)))
            } else {
                Toast.makeText(context, NO_YOUTUBE_LINK, Toast.LENGTH_LONG).show()
            }
        }
        binding.btnWebCiteDetails.setOnClickListener {
            if (skiPlace.webCite.isNotBlank()) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(skiPlace.webCite)))
            } else {
                Toast.makeText(context, NO_WEB_CITE, Toast.LENGTH_LONG).show()
            }
        }
        binding.btnWebCameraDetails.setOnClickListener {
            if (skiPlace.webCamera.isNotBlank()) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(skiPlace.webCamera)))
            } else {
                Toast.makeText(context, NO_WEB_CAMERAS, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setHowToGetArgs(skiPlace: SkiPlace) {
        howToGetArgs = SkiPlaceHowToGetArgs(
            skiPlace.howToGetText,
            skiPlace.howToGetPic,
            skiPlace.nameRus,
            context?.let { skiPlace.getGeoDataRus(it) } ?: String.EMPTY)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}