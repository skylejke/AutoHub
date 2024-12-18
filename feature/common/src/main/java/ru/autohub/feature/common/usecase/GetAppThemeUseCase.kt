package ru.autohub.feature.common.usecase

import kotlinx.coroutines.flow.Flow
import ru.autohub.core.storage.repository.SettingsRepository

class GetAppThemeUseCase(private val settingsRepository: SettingsRepository) {
    fun execute(): Flow<Boolean> = settingsRepository.getAppTheme()
}