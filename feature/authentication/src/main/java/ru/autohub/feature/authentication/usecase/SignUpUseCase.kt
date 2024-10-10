package ru.autohub.feature.authentication.usecase

import ru.autohub.core.storage.repository.AuthReposotory

class SignUpUseCase(private val authReposotory: AuthReposotory) {
    fun execute(email: String, password: String, callback: (Boolean) -> Unit) {
        authReposotory.signUp(email, password, callback)
    }
}