package com.example.autohub.di

import ru.autohub.feature.car_offers.usecase.AddToFavouriteUseCase
import ru.autohub.feature.car_offers.usecase.CheckIfCarIsFavoutriteUseCase
import ru.autohub.feature.car_offers.usecase.DeleteFromFavouriteUseCase
import ru.autohub.feature.car_offers.usecase.GetCarsUseCase
import ru.autohub.feature.common.usecase.GetCurrentUserUseCase
import ru.autohub.feature.car_offers.usecase.GetFavouritesUseCase
import ru.autohub.feature.authentication.usecase.SignInUseCase
import ru.autohub.feature.authentication.usecase.SignUpUseCase
import ru.autohub.feature.car_offers.usecase.SortCarsUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetCarsUseCase> {
        GetCarsUseCase(carRepository = get())
    }

    factory<SortCarsUseCase> {
        SortCarsUseCase(carRepository = get())
    }

    factory<ru.autohub.feature.search.usecase.SearchCarsByMakeUseCase> {
        ru.autohub.feature.search.usecase.SearchCarsByMakeUseCase(carRepository = get())
    }

    factory<GetFavouritesUseCase> {
        GetFavouritesUseCase(carDetailsRepository = get())
    }

    factory<AddToFavouriteUseCase> {
        AddToFavouriteUseCase(carDetailsRepository = get())
    }

    factory<CheckIfCarIsFavoutriteUseCase> {
        CheckIfCarIsFavoutriteUseCase(carDetailsRepository = get())
    }

    factory<ru.autohub.feature.search.usecase.ClearSearchHistoryUseCase> {
        ru.autohub.feature.search.usecase.ClearSearchHistoryUseCase(searchHistoryRepository = get())
    }

    factory<DeleteFromFavouriteUseCase> {
        DeleteFromFavouriteUseCase(carDetailsRepository = get())
    }

    factory<GetCurrentUserUseCase> {
        GetCurrentUserUseCase(authReposotory = get())
    }

    factory<ru.autohub.feature.search.usecase.LoadSearchHistoryUseCase> {
        ru.autohub.feature.search.usecase.LoadSearchHistoryUseCase(searchHistoryRepository = get())
    }

    factory<SignInUseCase> {
        SignInUseCase(authReposotory = get())
    }

    factory<ru.autohub.feature.settings.usecase.SignOutUseCase> {
        ru.autohub.feature.settings.usecase.SignOutUseCase(authReposotory = get())
    }

    factory<SignUpUseCase> {
        SignUpUseCase(authReposotory = get())
    }

    factory<ru.autohub.feature.search.usecase.UpdateSearchHistoryUseCase> {
        ru.autohub.feature.search.usecase.UpdateSearchHistoryUseCase(searchHistoryRepository = get())
    }

    factory<ru.autohub.feature.settings.usecase.GetAppThemeUseCase> {
        ru.autohub.feature.settings.usecase.GetAppThemeUseCase(settingsRepository = get())
    }

    factory<ru.autohub.feature.settings.usecase.ChangeAppThemeUseCase> {
        ru.autohub.feature.settings.usecase.ChangeAppThemeUseCase(settingsRepository = get())
    }
}