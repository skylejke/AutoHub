package com.example.autohub.app

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.example.autohub.di.appModule
import com.example.autohub.di.dataModule
import com.example.autohub.di.domainModule
import com.example.autohub.ui.MainActivity.Companion.DARK_THEME_ENABLED_KEY
import com.example.autohub.ui.MainActivity.Companion.THEME_PREFERENCES
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {


    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(appModule, domainModule, dataModule))
        }


        val sharedPreferences = getSharedPreferences(THEME_PREFERENCES, Context.MODE_PRIVATE)
        val isDarkThemeEnabled = sharedPreferences.getBoolean(DARK_THEME_ENABLED_KEY, false)

        applyTheme(isDarkThemeEnabled)
    }

    fun applyTheme(isDarkThemeEnabled: Boolean) {
        val mode = if (isDarkThemeEnabled) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }
        AppCompatDelegate.setDefaultNightMode(mode)
    }


}