package com.example.autohub.ui.authentication.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.autohub.data.repository.AuthRepositoryImpl
import com.example.autohub.data.storage.AuthStorageImpl
import com.example.autohub.domain.usecase.GetCurrentUserUseCase
import com.example.autohub.domain.usecase.SignUpUseCase

@Suppress("UNCHECKED_CAST")
class SignUpViewModelFactory : ViewModelProvider.Factory {

    private val authStorage by lazy { AuthStorageImpl() }

    private val authReposotory by lazy { AuthRepositoryImpl(authStorage) }

    private val signUpUseCase by lazy { SignUpUseCase(authReposotory) }

    private val getCurrentUserUseCase by lazy { GetCurrentUserUseCase(authReposotory) }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SignUpViewModel(
            signUpUseCase = signUpUseCase,
            getCurrentUserUseCase = getCurrentUserUseCase
        ) as T
    }
}