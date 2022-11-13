package com.epicteam1.skimountains.feature_auth.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.epicteam1.skimountains.R
import com.epicteam1.skimountains.databinding.FragmentPasswordResetBinding
import com.epicteam1.skimountains.feature_auth.presentation.viewModel.AuthViewModel
import com.epicteam1.skimountains.feature_ski_places.core.Constants.EMAIL_EMPTY
import com.epicteam1.skimountains.feature_ski_places.core.Constants.EMPTY_EMAIL_MESSAGE
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResetPasswordFragment : Fragment(R.layout.fragment_password_reset) {

    private var _binding: FragmentPasswordResetBinding? = null
    private val binding get() = _binding!!
    private val authViewModel by viewModel<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPasswordResetBinding.inflate(inflater, container, false)
        setUpWidgets()
        listenToChannels()
        return binding.root
    }

    private fun listenToChannels() {
        viewLifecycleOwner.lifecycleScope.launch {
            authViewModel.allEventsFlow.collect { event ->
                when (event) {
                    is AuthViewModel.AllEvents.Message -> {
                        Toast.makeText(requireContext(), event.message, Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_reset_pass_to_sign_in_fragment)
                    }
                    is AuthViewModel.AllEvents.Error -> {
                        binding.apply {
                            errorText.text = event.error
                        }
                    }
                    is AuthViewModel.AllEvents.ErrorCode -> {
                        if (event.code == EMAIL_EMPTY)
                            binding.apply {
                                userEmailEtvl.error = EMPTY_EMAIL_MESSAGE
                            }
                    }
                }

            }
        }
    }

    private fun setUpWidgets() {
        binding.apply {
            buttonResendPassword.setOnClickListener {
                val email = userEmailEtv.text.toString()
                authViewModel.verifySendPasswordReset(email)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}