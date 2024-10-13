package ru.autohub.feature.settings.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.autohub.feature.settings.ui.settings.SettingsViewModel
import ru.autohub.feature.settings.usecase.ChangeAppThemeUseCase
import ru.autohub.feature.settings.usecase.SignOutUseCase

val settingsModule = module {

    factory<ChangeAppThemeUseCase> {
        ChangeAppThemeUseCase(settingsRepository = get())
    }

    factory<SignOutUseCase> {
        SignOutUseCase(authReposotory = get())
    }

    viewModel<SettingsViewModel> {
        SettingsViewModel(
            signOutUseCase = get(),
            getCurrentUserUseCase = get(),
            getAppThemeUseCase = get(),
            changeAppThemeUseCase = get()
        )
    }
}