package ru.autohub.feature.authentication.ui.signup

import android.util.Patterns
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.autohub.feature.authentication.ui.AuthState
import ru.autohub.feature.authentication.usecase.SignUpUseCase

class SignUpViewModel(private val signUpUseCase: SignUpUseCase) : ViewModel() {

    fun signUpUser(userEmail: String, userPassword: String): StateFlow<AuthState> {
        val result = MutableStateFlow<AuthState>(AuthState.Loading)
        if (validateData(userEmail, userPassword)) {
            signUpUseCase.execute(userEmail, userPassword) { success ->
                result.value = if (success) {
                    AuthState.Success
                } else {
                    AuthState.Error
                }
            }
        }
        return result
    }

    private fun validateData(email: String, password: String): Boolean {
        return email.isNotEmpty() &&
                password.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}