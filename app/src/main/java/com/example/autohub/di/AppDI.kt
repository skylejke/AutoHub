package com.example.autohub.di

import com.example.autohub.ui.MainActivityViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<MainActivityViewModel> {
        MainActivityViewModel(getAppThemeUseCase = get())
    }
}