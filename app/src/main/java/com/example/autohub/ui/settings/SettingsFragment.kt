package com.example.autohub.ui.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.autohub.R
import com.example.autohub.app.App
import com.example.autohub.databinding.FragmentSettingsBinding
import com.example.autohub.ui.MainActivity.Companion.DARK_THEME_ENABLED_KEY
import com.example.autohub.ui.MainActivity.Companion.THEME_PREFERENCES
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    private val settingsViewModel by viewModel<SettingsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences =
            requireActivity().getSharedPreferences(THEME_PREFERENCES, Context.MODE_PRIVATE)
        val isDarkThemeEnabled = sharedPreferences.getBoolean(DARK_THEME_ENABLED_KEY, false)

        binding.darkThemeSwitch.isChecked = isDarkThemeEnabled

        settingsViewModel.getCurrentUser().observe(viewLifecycleOwner) {
            binding.username.text = it.email
        }

        binding.darkThemeSwitch.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().putBoolean(DARK_THEME_ENABLED_KEY, isChecked).apply()
            (binding.root.context.applicationContext as App).applyTheme(isChecked)
        }

        binding.logoutButton.setOnClickListener {
            settingsViewModel.signOut()
            findNavController().navigate(R.id.action_settingsFragment_to_signUpFragment)
        }

        binding.backIcon.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}