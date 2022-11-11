package com.epicteam1.skimountains.feature_auth.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.epicteam1.skimountains.R
import com.epicteam1.skimountains.databinding.FragmentPasswordResetBinding
import com.epicteam1.skimountains.feature_auth.presentation.viewModel.AuthViewModel
import kotlinx.coroutines.launch

class ResetPasswordFragment : Fragment(R.layout.fragment_password_reset) {

    private var _binding: FragmentPasswordResetBinding? = null
    private val binding get() = _binding
    private val viewModel: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPasswordResetBinding.inflate(inflater, container, false)
        setUpWidgets()
        listenToChannels()
        return binding?.root
    }

    private fun listenToChannels() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.allEventsFlow.collect { event ->
                when (event) {
                    is AuthViewModel.AllEvents.Message -> {
                        Toast.makeText(requireContext(), event.message, Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_reset_pass_to_sign_in_fragment)
                    }
                    is AuthViewModel.AllEvents.Error -> {
                        binding?.apply {
                            errorText.text = event.error
                        }
                    }
                    is AuthViewModel.AllEvents.ErrorCode -> {
                        if (event.code == 1)
                            binding?.apply {
                                userEmailEtvl.error = "email should not be empty!"
                            }
                    }
                }

            }
        }
    }

    private fun setUpWidgets() {
        binding?.apply {
            buttonResendPassword.setOnClickListener {
                val email = userEmailEtv.text.toString()
                viewModel.verifySendPasswordReset(email)
            }
        }
    }
}