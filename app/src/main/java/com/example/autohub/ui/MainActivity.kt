package com.example.autohub.ui


import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.autohub.R
import com.example.autohub.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.autohub.feature.authentication.utils.AuthBottomNavManager
import ru.autohub.feature.car_offers.HomeBottomNavManager

class MainActivity : AppCompatActivity(), AuthBottomNavManager, HomeBottomNavManager {

    private lateinit var binding: ActivityMainBinding
    private val mainActivityViewModel by viewModel<MainActivityViewModel>()

    private fun applyTheme(isDarkThemeEnabled: Boolean) {
        if (isDarkThemeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.navHostFragment) { v, insets ->
            val bars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
                        or WindowInsetsCompat.Type.displayCutout()
            )
            v.updatePadding(
                left = bars.left,
                top = bars.top,
                right = bars.right,
                bottom = bars.bottom,
            )
            WindowInsetsCompat.CONSUMED
        }

        lifecycleScope.launch {
            mainActivityViewModel.getAppTheme().collect { isDarkThemeEnabled ->
                applyTheme(isDarkThemeEnabled)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        setupWithNavController(
            binding.bottomNavigation,
            findNavController(this, R.id.nav_host_fragment)
        )
    }

    override fun onAuthBottomNavStateChanged() {
        hide()
    }

    override fun onHomeBottomNavStateChanged() {
        show()
    }

    private fun hide() {
        binding.bottomNavigation.isVisible = false
    }

    private fun show() {
        binding.bottomNavigation.isVisible = true
    }
}