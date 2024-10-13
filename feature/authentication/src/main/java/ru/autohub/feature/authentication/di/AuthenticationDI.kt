package ru.autohub.feature.authentication.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.autohub.feature.authentication.ui.signin.SignInViewModel
import ru.autohub.feature.authentication.ui.signup.SignUpViewModel
import ru.autohub.feature.authentication.usecase.SignInUseCase
import ru.autohub.feature.authentication.usecase.SignUpUseCase

val authenticationModule = module {

    factory<SignInUseCase> {
        SignInUseCase(authReposotory = get())
    }

    factory<SignUpUseCase> {
        SignUpUseCase(authReposotory = get())
    }

    viewModel<SignInViewModel> {
        SignInViewModel(signInUseCase = get(), getCurrentUserUseCase = get())
    }

    viewModel<SignUpViewModel> {
        SignUpViewModel(signUpUseCase = get())
    }
}