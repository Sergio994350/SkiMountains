package com.epicteam1.skimountains.feature_ski_places.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.epicteam1.skimountains.R
import com.epicteam1.skimountains.databinding.FragmentProfileBinding
import com.epicteam1.skimountains.feature_auth.presentation.viewModel.AuthViewModel
import com.epicteam1.skimountains.feature_ski_places.core.Constants.EMAIL_HAS_SEND
import com.epicteam1.skimountains.feature_ski_places.core.Constants.EMAIL_NOT_FOUND
import com.epicteam1.skimountains.feature_ski_places.core.Constants.EMPTY
import com.epicteam1.skimountains.feature_ski_places.core.Constants.LOG_OUT
import com.epicteam1.skimountains.feature_ski_places.core.Constants.LOG_OUT_SUCCESS
import com.epicteam1.skimountains.feature_ski_places.core.Constants.SIGN_UP
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val authViewModel by viewModel<AuthViewModel>()
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        registerObservers()
        getUser()

        return binding?.root
    }

    private fun getUser() {
        authViewModel.getCurrentUser()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun registerObservers() {
        authViewModel.currentUser.observe(viewLifecycleOwner) { user ->
            user?.let {
                binding?.apply { // user registered
                    val userEmail = it.email.toString()
                    tvEmailProfileFragment.text = userEmail
                    tvChangePassword.isVisible = true
                    tvChangePassword.setOnClickListener {
                        Toast.makeText(context, EMAIL_HAS_SEND, Toast.LENGTH_SHORT).show()
                        authViewModel.verifySendPasswordReset(userEmail)
                    }
                    signupButtonProfile.text = LOG_OUT
                    signupButtonProfile.setOnClickListener {
                        authViewModel.signOut()
                        Toast.makeText(context, LOG_OUT_SUCCESS, Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_profile_to_home_fragment)
                    }
                }
            } ?: binding?.apply { // user not registered
                val userEmail = String.EMPTY
                tvEmailProfileFragment.text = EMAIL_NOT_FOUND
                tvChangePassword.isVisible = false
                signupButtonProfile.text = SIGN_UP
                signupButtonProfile.setOnClickListener {
                    findNavController().navigate(R.id.action_profile_to_sign_up_fragment)
                }
            }
        }
    }
}