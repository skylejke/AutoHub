package com.example.autohub.ui

import androidx.lifecycle.ViewModel
import ru.autohub.feature.settings.usecase.GetAppThemeUseCase
import kotlinx.coroutines.flow.Flow

class MainActivityViewModel(
    private val getAppThemeUseCase: GetAppThemeUseCase
) : ViewModel() {
    fun getAppTheme(): Flow<Boolean> = getAppThemeUseCase.execute()
}