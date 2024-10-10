package ru.autohub.feature.settings.usecase

import ru.autohub.core.storage.repository.SettingsRepository

class ChangeAppThemeUseCase(private val settingsRepository: SettingsRepository) {
    suspend fun execute(isChecked: Boolean) = settingsRepository.changeAppTheme(isChecked)
}