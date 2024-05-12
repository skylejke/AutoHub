package com.example.autohub.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.autohub.data.repository.AuthRepositoryImpl
import com.example.autohub.data.storage.AuthStorageImpl
import com.example.autohub.domain.usecase.GetCurrentUserUseCase
import com.example.autohub.domain.usecase.SignOutUseCase

@Suppress("UNCHECKED_CAST")
class SettingsViewModelFactory : ViewModelProvider.Factory {
    private val authStorage by lazy { AuthStorageImpl() }

    private val authReposotory by lazy { AuthRepositoryImpl(authStorage) }

    private val signOutUseCase by lazy { SignOutUseCase(authReposotory) }

    private val getCurrentUserUseCase by lazy { GetCurrentUserUseCase(authReposotory) }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SettingsViewModel(
            signOutUseCase = signOutUseCase,
            getCurrentUserUseCase = getCurrentUserUseCase
        ) as T
    }
}