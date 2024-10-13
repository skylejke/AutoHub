package ru.autohub.feature.common.di

import org.koin.dsl.module
import ru.autohub.feature.common.usecase.GetAppThemeUseCase
import ru.autohub.feature.common.usecase.GetCurrentUserUseCase

val commonModule = module {
    factory<GetCurrentUserUseCase> {
        GetCurrentUserUseCase(authReposotory = get())
    }
    factory<GetAppThemeUseCase> {
        GetAppThemeUseCase(settingsRepository = get())
    }
}