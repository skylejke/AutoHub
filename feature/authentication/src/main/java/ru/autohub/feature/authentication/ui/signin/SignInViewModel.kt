package ru.autohub.feature.authentication.ui.signin

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.autohub.feature.authentication.ui.AuthState
import ru.autohub.feature.authentication.usecase.SignInUseCase
import ru.autohub.feature.common.usecase.GetCurrentUserUseCase

class SignInViewModel(
    private val signInUseCase: SignInUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

    fun signInUser(userEmail: String, userPassword: String): StateFlow<AuthState> {
        val result = MutableStateFlow<AuthState>(AuthState.Loading)
        if (validateData(userEmail, userPassword)) {
            signInUseCase.execute(userEmail, userPassword) { success ->
                result.value = if (success) {
                    AuthState.Success
                } else {
                    AuthState.Error
                }
            }
        } else {
            result.value = AuthState.Error
        }
        return result
    }


    fun getCurrentUser(): StateFlow<FirebaseUser?> {
        val user = MutableStateFlow<FirebaseUser?>(null)
        user.value = getCurrentUserUseCase.execute()
        return user
    }

    private fun validateData(email: String, password: String): Boolean {
        return email.isNotEmpty() &&
                password.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}