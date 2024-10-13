package ru.autohub.feature.settings.usecase

import ru.autohub.core.storage.repository.AuthReposotory

internal class SignOutUseCase(private val authReposotory: AuthReposotory) {
    fun execute() {
        authReposotory.signOut()
    }
}