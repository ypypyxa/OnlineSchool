package com.pivnoydevelopment.onlineschool.onboarding.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.pivnoydevelopment.onlineschool.R
import com.pivnoydevelopment.onlineschool.databinding.FragmentOnboardingBinding
import com.pivnoydevelopment.onlineschool.onboarding.model.Destination
import org.koin.androidx.viewmodel.ext.android.viewModel


class OnboardingFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingBinding
    private val viewModel: OnboardingViewModel by viewModel<OnboardingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
        setupObservers()
    }

    private fun setupListeners() {
        binding.continueButton.setOnClickListener() {
            findNavController().navigate(R.id.action_onboardingFragment_to_authFragment)
        }
    }

    private fun setupObservers() {
        viewModel.navigateTo.observe(viewLifecycleOwner) { destination ->
            when (destination) {
                Destination.SEARCH -> {
                    findNavController().navigate(R.id.action_onboardingFragment_to_searchFragment)
                }
                null -> { }
            }
        }
    }
}