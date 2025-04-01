package com.pivnoydevelopment.onlineschool.authorization.ui

import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.core.net.toUri
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.pivnoydevelopment.onlineschool.R
import com.pivnoydevelopment.onlineschool.databinding.FragmentAuthBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding
    private val viewModel: AuthViewModel by viewModel<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupListeners()
        setupObservers()
    }

    private fun setupViews() {

        binding.emailInput.doAfterTextChanged {
            viewModel.onEmailChanged(it.toString())
        }
        binding.emailInput.filters = arrayOf(InputFilter { source, _, _, _, _, _ ->
            if (source.any { it.code in 0x0400..0x04FF }) "" else null
        })

        binding.passwordInput.doAfterTextChanged {
            viewModel.onPasswordChanged(it.toString())
        }

    }

    private fun setupListeners() {

        binding.vkButton.setOnClickListener {
            val intent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = "https://vk.com".toUri()
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            requireContext().startActivity(intent)
        }

        binding.okButton.setOnClickListener {
            val intent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = "https://ok.ru/".toUri()
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            requireContext().startActivity(intent)
        }

        binding.loginButton.setOnClickListener {
            viewModel.saveEmail()
            findNavController().navigate(R.id.action_authFragment_to_searchFragment)
        }

        binding.forgotPassword.setOnClickListener {
            showDialog()
        }

    }

    private fun setupObservers() {
        viewModel.isLoginEnabled.observe(viewLifecycleOwner) { enabled ->
            binding.loginButton.isEnabled = enabled
        }
    }

    private fun showDialog() {
        MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogStyle)
            .setTitle(requireContext().getString(R.string.dialog_title))
            .setMessage(requireContext().getString(R.string.dialog_message))
            .setPositiveButton(requireContext().getString(R.string.dialog_dismiss)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}