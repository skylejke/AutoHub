package com.example.autohub.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.autohub.domain.usecase.GetCurrentUserUseCase
import com.example.autohub.domain.usecase.SignOutUseCase
import com.google.firebase.auth.FirebaseUser

class SettingsViewModel(
    private val signOutUseCase: SignOutUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {
    fun signOut() {
        signOutUseCase.execute()
    }

    fun getCurrentUser(): LiveData<FirebaseUser> {
        val user = MutableLiveData<FirebaseUser>()
        user.value = getCurrentUserUseCase.execute()
        return user
    }
}