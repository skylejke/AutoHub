package com.example.autohub


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.autohub.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        setupWithNavController(
            binding.bottomNavigation,
            findNavController(this, R.id.nav_host_fragment)
        )
    }

    companion object {
        const val THEME_PREFERENCES = "ThemePrefs"

        const val DARK_THEME_ENABLED_KEY = "isDarkThemeEnabled"

        const val SEARCH_HISTORY_PREFERENCES = "SearchHistoryPrefs"

        const val SEARCH_HISTORY_KEY = "searchHistory"
    }

}