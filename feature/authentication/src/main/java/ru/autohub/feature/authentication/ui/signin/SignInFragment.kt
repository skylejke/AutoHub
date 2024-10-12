package ru.autohub.feature.authentication.ui.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.autohub.feature.authentication.utils.AuthBottomNavManager
import ru.autohub.feature.authentication.databinding.FragmentSignInBinding
import ru.autohub.feature.authentication.ui.AuthState
import ru.autohub.feature.common.utils.NavigableFragment


class SignInFragment : NavigableFragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val signInViewModel by viewModel<SignInViewModel>()

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            signInViewModel.getCurrentUser().collect { user ->
                if (user != null) {
                    navigator.fromSignInFragmentToHomeFragment()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentSignInBinding.inflate(layoutInflater).also {
        _binding = it
        (requireActivity() as AuthBottomNavManager).onAuthBottomNavStateChanged()
        setListeners()
    }.root

    private fun setListeners() {
        binding.signInBtn.setOnClickListener {

            val email = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()

            if (email.isEmpty()) {
                Toast.makeText(requireContext(), "Enter email", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                Toast.makeText(requireContext(), "Enter password", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                signInViewModel.signInUser(email, password).collect { state ->
                    when (state) {
                        is AuthState.Loading -> {
                            // "Сделать индикатор загрузки или сделайте что-то подобное"
                        }

                        is AuthState.Success -> {
                            Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT)
                                .show()
                            navigator.fromSignInFragmentToHomeFragment()
                        }

                        is AuthState.Error -> {
                            Toast.makeText(
                                requireContext(),
                                "Authentication failed",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }

        }

        binding.signUpBtn.setOnClickListener {
            navigator.fromSignInFragmentToSignUpFragment()
        }

        binding.continueAsAGuestBtn.setOnClickListener {
            navigator.fromSignInFragmentToHomeFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}