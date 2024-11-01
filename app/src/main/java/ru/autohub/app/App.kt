package ru.autohub.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.autohub.di.appModule
import ru.autohub.di.coreModule
import ru.autohub.di.navigationModule
import ru.autohub.feature.authentication.di.authenticationModule
import ru.autohub.feature.car_offers.di.carOffersModule
import ru.autohub.feature.common.di.commonModule
import ru.autohub.feature.search.di.searchModule
import ru.autohub.feature.settings.di.settingsModule

class App : Application() {

    private val moduleList = listOf(
        appModule,
        coreModule,
        navigationModule,
        authenticationModule,
        carOffersModule,
        searchModule,
        settingsModule,
        commonModule
    )

    override fun onCreate() {
        super.onCreate()
        setUpDI()
    }

    private fun setUpDI() {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(moduleList)
        }
    }
}