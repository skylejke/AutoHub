package ru.autohub.feature.authentication.ui

sealed class AuthState {
    data object Loading : AuthState()
    data object Success : AuthState()
    data object Error : AuthState()
}
