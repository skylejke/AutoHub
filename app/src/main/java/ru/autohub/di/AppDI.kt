package ru.autohub.di

import ru.autohub.ui.MainActivityViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<MainActivityViewModel> {
        MainActivityViewModel(getAppThemeUseCase = get())
    }
}