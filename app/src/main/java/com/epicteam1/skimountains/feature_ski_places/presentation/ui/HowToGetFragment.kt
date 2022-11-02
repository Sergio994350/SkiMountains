package com.epicteam1.skimountains.feature_ski_places.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.epicteam1.skimountains.R
import com.epicteam1.skimountains.databinding.FragmentHowtogetBinding

class HowToGetFragment : Fragment(R.layout.fragment_howtoget) {

    private lateinit var binding: FragmentHowtogetBinding

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
        // TODO
    }
}