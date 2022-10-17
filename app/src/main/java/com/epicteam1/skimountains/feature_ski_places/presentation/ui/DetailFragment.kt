package com.epicteam1.skimountains.feature_ski_places.presentation.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.epicteam1.skimountains.MainActivity
import com.epicteam1.skimountains.R
import com.epicteam1.skimountains.feature_ski_places.domain.util.Resource
import com.epicteam1.skimountains.feature_ski_places.presentation.viewModel.SkiViewModel
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment(R.layout.fragment_detail) {

    lateinit var viewModel: SkiViewModel
//    val args: DetailsFragmentArgs by navArgs<>() // TODO

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
//        val skiPlace = args.details
        card_view_back.setOnClickListener {
            findNavController().navigate(R.id.action_details_to_home2)
        }

        card_view_save_ski_place_details.setOnClickListener {
//            viewModel.saveSkiPlace(skiPlace) // TODO: запись skiPlace в БД по клику
            Snackbar.make(view, "SkiPlace Saved", Snackbar.LENGTH_SHORT).apply {
                animationMode = BaseTransientBottomBar.ANIMATION_MODE_SLIDE
                setBackgroundTint(Color.DKGRAY)
                setTextColor(Color.WHITE)
                show()
            }
        }

//        viewModel.getDetails(skiPlace.skiPlaceId)  // TODO

        viewModel.details.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { place ->
                        for (item in place.dataSkiPlacesList) {
                            Glide.with(this).load(item.mainPic).into(image_ski_place_big_details)
                            name_ski_place_details.text =
                                "${item.nameRus}, ${item.regionRus} (${item.regionBig})"
                            web_cite_details.text = item.webCite
                            web_cameras_details.text = item.webCamera
                            technical_data_details.text = item.technicalData
                            description_data_details.text = item.descriptionData
                            geo_data_details.text = item.geoData

                            // go to youtube url
                            play.setOnClickListener {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.youTubeLink))
                                startActivity(intent)
                            }
                        }
                    }
                }
                is Resource.Error -> {
                    response.message.let { msg ->
                        Toast.makeText(activity, "Error: $msg", Toast.LENGTH_LONG).show()
//                        Log.e(TAG, "Error: $msg")
                    }
                }
                else -> {}
            }
        })
    }
}