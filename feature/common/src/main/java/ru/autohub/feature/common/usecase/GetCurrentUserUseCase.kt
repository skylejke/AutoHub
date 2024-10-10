package ru.autohub.feature.common.usecase

import com.google.firebase.auth.FirebaseUser
import ru.autohub.core.storage.repository.AuthReposotory

class GetCurrentUserUseCase(private val authReposotory: AuthReposotory) {
    fun execute(): FirebaseUser? {
        return authReposotory.getCurrentUser()
    }
}