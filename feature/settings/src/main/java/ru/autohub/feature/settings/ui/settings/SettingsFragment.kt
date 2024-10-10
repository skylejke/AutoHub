package ru.autohub.feature.settings.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.autohub.feature.common.utils.NavigableFragment
import ru.autohub.feature.settings.R
import ru.autohub.feature.settings.databinding.FragmentSettingsBinding

class SettingsFragment : NavigableFragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val settingsViewModel by viewModel<SettingsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentSettingsBinding.inflate(layoutInflater).also {
        _binding = it
        setViews()
        setListeners()
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setViews() {
        lifecycleScope.launch {
            launch {
                settingsViewModel.getAppTheme().collect { isDarkThemeEnabled ->
                    binding.settingsContainer.darkThemeSwitch.isChecked = isDarkThemeEnabled
                }
            }
            launch {
                settingsViewModel.getCurrentUser().collect { user ->
                    binding.username.text =
                        user?.email ?: getString(R.string.log_in_to_get_additional_features)
                    binding.logoutButton.isVisible = user != null
                    binding.logInButton.isVisible = user == null
                }
            }
        }
    }

    private fun setListeners() {
        binding.settingsContainer.darkThemeSwitch.setOnClickListener {
            settingsViewModel.changeAppTheme(binding.settingsContainer.darkThemeSwitch.isChecked)
        }

        binding.settingsContainer.aboutApp.setOnClickListener {
            navigator.fromSettingsFragmentToAboutFragment()
        }

        binding.logoutButton.setOnClickListener {
            settingsViewModel.signOut()
            navigator.fromSettingsFragmentToSignInFragment()
        }

        binding.fragmentSettingsToolbar.backIcon.setOnClickListener {
            navigator.popBackStack()
        }

        binding.logInButton.setOnClickListener {
            navigator.fromSettingsFragmentToSignInFragment()
        }
    }
}