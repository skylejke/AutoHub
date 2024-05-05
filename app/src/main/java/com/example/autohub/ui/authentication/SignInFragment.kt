package com.example.autohub.ui.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.autohub.R
import com.example.autohub.databinding.FragmentSignInBinding
import com.example.autohub.ui.MainActivity


class SignInFragment : Fragment() {


    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as MainActivity).hideBottomNavigation()
        binding = FragmentSignInBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.signInBtn.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_mainFragment)
        }

        binding.signUpBtn.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }

}