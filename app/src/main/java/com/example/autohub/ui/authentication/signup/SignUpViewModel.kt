package com.example.autohub.ui.authentication.signup

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.autohub.domain.usecase.SignUpUseCase

class SignUpViewModel(private val signUpUseCase: SignUpUseCase) : ViewModel() {

    fun signUpUser(userEmail: String, userPassword: String): LiveData<Boolean> {
        val result = MutableLiveData<Boolean>()
        if (validateData(userEmail, userPassword)) {
            signUpUseCase.execute(userEmail, userPassword) { success ->
                result.value = success
            }
        }
        return result
    }

    private fun validateData(email: String, password: String): Boolean {
        return email.isNotEmpty() &&
                password.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}