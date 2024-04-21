package com.example.autohub

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.autohub.MainActivity.Companion.DARK_THEME_ENABLED_KEY
import com.example.autohub.MainActivity.Companion.THEME_PREFERENCES
import com.example.autohub.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = requireActivity().getSharedPreferences(THEME_PREFERENCES, Context.MODE_PRIVATE)
        val isDarkThemeEnabled = sharedPreferences.getBoolean(DARK_THEME_ENABLED_KEY, false)

        // Устанавливаем состояние свитча в соответствии с сохраненным значением
        binding.darkThemeSwitch.isChecked = isDarkThemeEnabled

        binding.darkThemeSwitch.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().putBoolean(DARK_THEME_ENABLED_KEY, isChecked).apply()
            (binding.root.context.applicationContext as App).applyTheme(isChecked)
        }

        binding.clearSearchHistory.setOnClickListener {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}