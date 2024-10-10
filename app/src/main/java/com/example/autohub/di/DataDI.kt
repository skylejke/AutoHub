package com.example.autohub.di

import org.koin.dsl.module
import ru.autohub.core.storage.AuthStorage
import ru.autohub.core.storage.CarDetailsStorage
import ru.autohub.core.storage.CarStorage
import ru.autohub.core.storage.SearchHistoryStorage
import ru.autohub.core.storage.SettingsStorage
import ru.autohub.core.storage.di.StorageProvider
import ru.autohub.core.storage.repository.AuthReposotory
import ru.autohub.core.storage.repository.CarDetailsRepository
import ru.autohub.core.storage.repository.CarRepository
import ru.autohub.core.storage.repository.SearchHistoryRepository
import ru.autohub.core.storage.repository.SettingsRepository

val dataModule = module {

    single<CarStorage> {
        StorageProvider.provideCarStorage()
    }

    single<CarRepository> {
        StorageProvider.provideCarRepository(carStorage = get())
    }

    single<AuthStorage> {
        StorageProvider.provideAuthStorage()
    }

    single<AuthReposotory> {
        StorageProvider.provideAuthRepository(authStorage = get())
    }

    single<CarDetailsStorage> {
        StorageProvider.provideCarDetailsStorage()
    }

    single<CarDetailsRepository> {
        StorageProvider.provideCarDetailsRepository(carDetailsStorage = get())
    }

    single<SearchHistoryStorage> {
        StorageProvider.provideSearchHistoryStorage(context = get())
    }

    single<SearchHistoryRepository> {
        StorageProvider.provideSearchHistoryRepository(searchHistoryStorage = get())
    }

    single<SettingsStorage> {
        StorageProvider.provideSettingsStorage(context = get())
    }

    single<SettingsRepository> {
        StorageProvider.provideSettingsRepository(settingsStorage = get())
    }
}