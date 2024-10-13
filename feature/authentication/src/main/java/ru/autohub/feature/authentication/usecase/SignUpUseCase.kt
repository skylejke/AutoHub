package ru.autohub.feature.authentication.usecase

import ru.autohub.core.storage.repository.AuthReposotory

internal class SignUpUseCase(private val authReposotory: AuthReposotory) {
    fun execute(email: String, password: String, callback: (Boolean) -> Unit) {
        authReposotory.signUp(email, password, callback)
    }
}