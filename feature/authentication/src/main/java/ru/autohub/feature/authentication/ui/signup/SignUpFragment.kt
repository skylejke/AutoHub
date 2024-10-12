package ru.autohub.feature.authentication.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.autohub.feature.authentication.utils.AuthBottomNavManager
import ru.autohub.feature.authentication.databinding.FragmentSignUpBinding
import ru.autohub.feature.authentication.ui.AuthState
import ru.autohub.feature.common.utils.NavigableFragment


class SignUpFragment : NavigableFragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val signUpViewModel by viewModel<SignUpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentSignUpBinding.inflate(layoutInflater)
        .also {
            _binding = it
            (requireActivity() as AuthBottomNavManager).onAuthBottomNavStateChanged()
            setListeners()
        }.root

    private fun setListeners() {

        binding.signUpBtn.setOnClickListener {

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
                signUpViewModel.signUpUser(email, password).collect { state ->
                    when (state) {
                        is AuthState.Loading -> {
                            // "Сделать индикатор загрузки или что-то подобное"
                        }

                        is AuthState.Success -> {
                            Toast.makeText(requireContext(), "Account created", Toast.LENGTH_SHORT)
                                .show()
                            navigator.fromSignUpFragmentToHomeFragment()
                        }

                        is AuthState.Error -> {
                            Toast.makeText(
                                requireContext(),
                                "Authentication failed",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
                }
            }
        }
        binding.signInBtn.setOnClickListener {
            navigator.fromSignUpFragmentToSignInFragment()
        }

        binding.continueAsAGuestBtn.setOnClickListener {
            navigator.fromSignUpFragmentToHomeFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}