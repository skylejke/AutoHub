package ru.autohub.core.storage.repository

import kotlinx.coroutines.flow.Flow
import ru.autohub.core.storage.SettingsStorage

internal class SettingsRepositoryImpl(private val settingsStorage: SettingsStorage) :
    SettingsRepository {
    override fun getAppTheme(): Flow<Boolean> = settingsStorage.getAppTheme()

    override suspend fun changeAppTheme(isChecked: Boolean) =
        settingsStorage.changeAppTheme(isChecked)
}