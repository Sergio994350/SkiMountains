package com.epicteam1.skimountains.feature_ski_places.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.epicteam1.skimountains.R
import com.epicteam1.skimountains.databinding.FragmentHowtogetBinding
import com.epicteam1.skimountains.feature_ski_places.core.Constants
import com.epicteam1.skimountains.feature_ski_places.presentation.model.SkiPlaceHowToGetArgs

class HowToGetFragment : Fragment(R.layout.fragment_howtoget) {

    private var _binding: FragmentHowtogetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHowtogetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
          val howToGetArgs: SkiPlaceHowToGetArgs = arguments?.get(Constants.HOW_TO_GET_ARGS) as SkiPlaceHowToGetArgs
          setHowToGetData(howToGetArgs)
    }

    private fun setHowToGetData(howToGetArgs: SkiPlaceHowToGetArgs) {
        with(binding) {
            tvSubtitleNameHowToGet.text = howToGetArgs.howToGetNameRus
            tvDescriptionHowToGet.text = howToGetArgs.howToGetText
            tvHowToGetGeoData.text = howToGetArgs.howToGetGeoData
        }
        Glide.with(this).load(howToGetArgs.howToGetPic).into(binding.imageViewHowToGet)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
