package com.example.autohub.ui.authentication.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.autohub.R
import com.example.autohub.databinding.FragmentSignInBinding
import com.example.autohub.ui.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding

    private val signInViewModel by viewModel<SignInViewModel>()

    override fun onStart() {
        super.onStart()
        signInViewModel.getCurrentUser().observe(viewLifecycleOwner) { user ->
            if (user != null) {
                findNavController().navigate(R.id.action_signInFragment_to_homeFragment)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        lifecycle.addObserver((activity as MainActivity).BottomNavManager())
        binding = FragmentSignInBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

            signInViewModel.signInUser(email, password).observe(viewLifecycleOwner) { success ->
                if (success) {
                    Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT)
                        .show()
                    findNavController().navigate(R.id.action_signInFragment_to_homeFragment)
                } else {
                    Toast.makeText(requireContext(), "Authentication failed", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        binding.signUpBtn.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        binding.continueAsAGuestBtn.setOnClickListener{
            findNavController().navigate(R.id.action_signInFragment_to_homeFragment)
        }
    }
}