package com.example.autohub.ui.authentication.signin

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.autohub.domain.usecase.GetCurrentUserUseCase
import com.example.autohub.domain.usecase.SignInUseCase
import com.google.firebase.auth.FirebaseUser

class SignInViewModel(
    private val signInUseCase: SignInUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

    fun signInUser(userEmail: String, userPassword: String): LiveData<Boolean> {
        val result = MutableLiveData<Boolean>()
        if (validateData(userEmail, userPassword)) {
            signInUseCase.execute(userEmail, userPassword) { success ->
                result.value = success
            }
        }
        return result
    }

    fun getCurrentUser(): LiveData<FirebaseUser> {
        val user = MutableLiveData<FirebaseUser>()
        user.value = getCurrentUserUseCase.execute()
        return user
    }

    private fun validateData(email: String, password: String): Boolean {
        return email.isNotEmpty() &&
                password.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}