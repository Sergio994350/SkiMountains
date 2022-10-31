package com.epicteam1.skimountains.feature_ski_places.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.epicteam1.skimountains.R
import com.epicteam1.skimountains.databinding.FragmentHowtogetBinding
import com.epicteam1.skimountains.feature_ski_places.core.Constants
import com.epicteam1.skimountains.feature_ski_places.core.getGeoDataRus
import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace
import com.epicteam1.skimountains.feature_ski_places.presentation.viewModel.SkiPlaceViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HowToGetFragment : Fragment(R.layout.fragment_howtoget) {

    private lateinit var binding: FragmentHowtogetBinding
    private val skiPlaceViewModel by viewModel<SkiPlaceViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHowtogetBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val skiPlaceArg: String = arguments?.getString(Constants.DETAILS)!!

        setObservers()
        setOnClickListenersHtg()
        skiPlaceViewModel.getSkiPlaceDetailsById(skiPlaceArg)
    }

    private fun setObservers() {
        skiPlaceViewModel.skiPlaceDetailLoaded.observe(viewLifecycleOwner, ::setSkiPlaceDataHtg)
    }

    private fun setOnClickListenersHtg() {
        card_view_back.setOnClickListener {
            findNavController().apply { popBackStack() }
        }
    }

    private fun setSkiPlaceDataHtg(skiPlace: SkiPlace) {
        Glide.with(this).load(skiPlace.howToGetPic).into(binding.imageViewHowToGet)
        binding.tvHowToGetGeoData.text = context?.let { skiPlace.getGeoDataRus(it) }
        binding.tvDescriptionHowToGet.text = skiPlace.howToGetText
    }
}