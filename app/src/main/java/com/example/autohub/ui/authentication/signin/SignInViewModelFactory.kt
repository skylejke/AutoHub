package com.example.autohub.ui.authentication.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.autohub.data.repository.AuthRepositoryImpl
import com.example.autohub.data.storage.AuthStorageImpl
import com.example.autohub.domain.usecase.SignInUseCase

@Suppress("UNCHECKED_CAST")
class SignInViewModelFactory : ViewModelProvider.Factory {

    private val authStorage by lazy { AuthStorageImpl() }

    private val authReposotory by lazy { AuthRepositoryImpl(authStorage) }

    private val signInUseCase by lazy { SignInUseCase(authReposotory) }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SignInViewModel(signInUseCase = signInUseCase) as T
    }
}