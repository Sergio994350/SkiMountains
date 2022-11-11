package com.epicteam1.skimountains.feature_ski_places.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.epicteam1.skimountains.R
import com.epicteam1.skimountains.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding?.apply {
            signupButtonProfile.setOnClickListener {
                findNavController().navigate(R.id.action_profile_to_sign_up_fragment)
            }
        }
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}