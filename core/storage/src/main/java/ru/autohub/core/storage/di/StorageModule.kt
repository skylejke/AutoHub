package ru.autohub.core.storage.di

import android.content.Context
import ru.autohub.core.storage.AuthStorage
import ru.autohub.core.storage.CarDetailsStorage
import ru.autohub.core.storage.CarStorage
import ru.autohub.core.storage.SearchHistoryStorage
import ru.autohub.core.storage.SettingsStorage
import ru.autohub.core.storage.impl.AuthStorageImpl
import ru.autohub.core.storage.impl.CarDetailsStorageImpl
import ru.autohub.core.storage.impl.CarStorageImpl
import ru.autohub.core.storage.impl.SearchHistoryStorageImpl
import ru.autohub.core.storage.impl.SettingsStorageImpl
import ru.autohub.core.storage.repository.AuthRepositoryImpl
import ru.autohub.core.storage.repository.CarDetailsRepositoryImpl
import ru.autohub.core.storage.repository.CarRepositoryImpl
import ru.autohub.core.storage.repository.SearchHistoryRepositoryImpl
import ru.autohub.core.storage.repository.SettingsRepositoryImpl
import ru.autohub.core.storage.repository.AuthReposotory
import ru.autohub.core.storage.repository.CarDetailsRepository
import ru.autohub.core.storage.repository.CarRepository
import ru.autohub.core.storage.repository.SearchHistoryRepository
import ru.autohub.core.storage.repository.SettingsRepository

object StorageProvider {
    fun provideSettingsStorage(context: Context): SettingsStorage =
        SettingsStorageImpl(context = context)

    fun provideSettingsRepository(settingsStorage: SettingsStorage): SettingsRepository =
        SettingsRepositoryImpl(settingsStorage = settingsStorage)

    fun provideSearchHistoryStorage(context: Context): SearchHistoryStorage =
        SearchHistoryStorageImpl(context = context)

    fun provideSearchHistoryRepository(searchHistoryStorage: SearchHistoryStorage): SearchHistoryRepository =
        SearchHistoryRepositoryImpl(searchHistoryStorage = searchHistoryStorage)

    fun provideCarStorage(): CarStorage = CarStorageImpl()

    fun provideCarRepository(carStorage: CarStorage): CarRepository =
        CarRepositoryImpl(carStorage = carStorage)

    fun provideCarDetailsStorage(): CarDetailsStorage = CarDetailsStorageImpl()

    fun provideCarDetailsRepository(carDetailsStorage: CarDetailsStorage): CarDetailsRepository =
        CarDetailsRepositoryImpl(carDetailsStorage = carDetailsStorage)

    fun provideAuthStorage(): AuthStorage = AuthStorageImpl()

    fun provideAuthRepository(authStorage: AuthStorage): AuthReposotory =
        AuthRepositoryImpl(authStorage = authStorage)
}