package com.example.autohub.ui


import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.autohub.R
import com.example.autohub.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences(THEME_PREFERENCES, Context.MODE_PRIVATE)
        val isDarkThemeEnabled = sharedPreferences.getBoolean(DARK_THEME_ENABLED_KEY, false)

        applyTheme(isDarkThemeEnabled)
    }

    override fun onStart() {
        super.onStart()
        setupWithNavController(
            binding.bottomNavigation,
            findNavController(this, R.id.nav_host_fragment)
        )
    }

    inner class BottomNavManager() : LifecycleEventObserver {

        private fun hide() {
            binding.bottomNavigation.visibility = View.GONE
        }

        private fun show() {
            binding.bottomNavigation.visibility = View.VISIBLE
        }

        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            if (event == Lifecycle.Event.ON_CREATE) {
                hide()
            } else if (event == Lifecycle.Event.ON_PAUSE) {
                show()
            }
        }
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