package com.epicteam1.skimountains.feature_auth.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.epicteam1.skimountains.R
import com.epicteam1.skimountains.databinding.FragmentSignInBinding
import com.epicteam1.skimountains.feature_auth.presentation.viewModel.AuthViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private val authViewModel by viewModel<AuthViewModel>()
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!
    private val TAG = "SignInFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater , container , false)
        listenToChannels()
        registerObservers()
        binding.apply {
            signInButton.setOnClickListener {
                val email = emailEt.text.toString()
                val password = passET.text.toString()
                authViewModel.signInUser(email, password)
            }

            tvNotRegisteredYet.setOnClickListener {
                findNavController().navigate(R.id.action_sign_in_to_sign_up_fragment)
            }

            tvForgotPassword.setOnClickListener {
                findNavController().navigate(R.id.action_sign_in_to_reset_password_fragment)
            }
        }
        return binding.root
    }

    private fun registerObservers() {
        authViewModel.currentUser.observe(viewLifecycleOwner) { user ->
            user?.let {
                findNavController().navigate(R.id.action_sign_in_to_home_fragment)
            }
        }
    }

    private fun listenToChannels() {
        viewLifecycleOwner.lifecycleScope.launch {
            authViewModel.allEventsFlow.collect { event ->
                when(event){
                    is AuthViewModel.AllEvents.Error -> {
                        binding.apply {
                            errorTxt.text =  event.error
                        }
                    }
                    is AuthViewModel.AllEvents.Message -> {
                        Toast.makeText(requireContext(), event.message, Toast.LENGTH_SHORT).show()
                    }
                    is AuthViewModel.AllEvents.ErrorCode -> {
                        if (event.code == 1)
                            binding.apply {
                                emailEt.error = (R.string.enter_your_email).toString()
                            }

                        if(event.code == 2)
                            binding.apply {
                                passET.error = (R.string.enter_your_password).toString()
                            }
                    }
                    else ->{
                        Log.d(TAG, "listenToChannels: No event received")
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}