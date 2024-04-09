package com.example.autohub


import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Получаем SharedPreferences
        val sharedPreferences = getSharedPreferences(THEME_PREFERENCES, Context.MODE_PRIVATE)
        // Получаем сохраненное состояние темы (по умолчанию - светлая тема)
        val isDarkThemeEnabled = sharedPreferences.getBoolean(DARK_THEME_ENABLED_KEY, false)
        // Применяем выбранную тему
        applyTheme(isDarkThemeEnabled)
    }

    override fun onStart() {
        super.onStart()
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController = findNavController(this, R.id.nav_host_fragment)
        setupWithNavController(bottomNavigationView, navController)
    }

    companion object {
        const val THEME_PREFERENCES = "ThemePrefs"

        const val DARK_THEME_ENABLED_KEY = "isDarkThemeEnabled"

        fun applyTheme(isDarkThemeEnabled: Boolean) {
            val mode = if (isDarkThemeEnabled) {
                AppCompatDelegate.MODE_NIGHT_YES
            } else {
                AppCompatDelegate.MODE_NIGHT_NO
            }
            AppCompatDelegate.setDefaultNightMode(mode)
        }
    }

}