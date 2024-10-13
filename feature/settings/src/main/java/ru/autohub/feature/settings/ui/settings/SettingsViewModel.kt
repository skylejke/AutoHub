package ru.autohub.feature.settings.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.autohub.feature.common.usecase.GetCurrentUserUseCase
import ru.autohub.feature.settings.usecase.ChangeAppThemeUseCase
import ru.autohub.feature.common.usecase.GetAppThemeUseCase
import ru.autohub.feature.settings.usecase.SignOutUseCase

internal class SettingsViewModel(
    private val signOutUseCase: SignOutUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getAppThemeUseCase: GetAppThemeUseCase,
    private val changeAppThemeUseCase: ChangeAppThemeUseCase
) : ViewModel() {

    fun getAppTheme(): Flow<Boolean> = getAppThemeUseCase.execute()

    fun changeAppTheme(isChecked: Boolean) {
        viewModelScope.launch {
            changeAppThemeUseCase.execute(isChecked)
        }
    }

    fun signOut() = signOutUseCase.execute()

    fun getCurrentUser(): StateFlow<FirebaseUser?> {
        val user = MutableStateFlow<FirebaseUser?>(null)
        user.value = getCurrentUserUseCase.execute()
        return user
    }
}