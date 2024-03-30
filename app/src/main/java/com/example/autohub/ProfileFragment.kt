package com.example.autohub

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val themeSwitch = view.findViewById<SwitchCompat>(R.id.darkTheme_switch)
        // Получаем SharedPreferences
        val sharedPreferences =
            requireActivity().getSharedPreferences("ThemePrefs", Context.MODE_PRIVATE)
        // Получаем сохраненное состояние темы (по умолчанию - светлая тема)
        val isDarkThemeEnabled = sharedPreferences.getBoolean("isDarkThemeEnabled", false)
        // Устанавливаем состояние переключателя в соответствии с сохраненным значением
        themeSwitch.isChecked = isDarkThemeEnabled
        // Устанавливаем слушатель для изменения состояния переключателя
        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Сохраняем состояние переключателя в SharedPreferences
            sharedPreferences.edit().putBoolean("isDarkThemeEnabled", isChecked).apply()

            // Применяем выбранную тему
            applyTheme(isChecked)
        }
    }

    private fun applyTheme(isDarkThemeEnabled: Boolean) {
        val mode = if (isDarkThemeEnabled) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }
        AppCompatDelegate.setDefaultNightMode(mode)
    }
}