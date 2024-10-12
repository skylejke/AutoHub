package com.example.autohub.di

import com.example.autohub.ui.MainActivityViewModel
import ru.autohub.feature.common.utils.ScreenSwitchable
import ru.autohub.feature.authentication.ui.signin.SignInViewModel
import ru.autohub.feature.authentication.ui.signup.SignUpViewModel
import ru.autohub.feature.car_offers.ui.cardetails.CarDetailsViewModel
import ru.autohub.feature.car_offers.ui.favourite.FavouriteViewModel
import ru.autohub.feature.car_offers.ui.home.HomeViewModel
import ru.autohub.feature.settings.ui.settings.SettingsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<HomeViewModel> {
        HomeViewModel(
            getCarsUseCase = get(),
            sortCarsUseCase = get()
        )
    }

    viewModel<SignInViewModel> {
        SignInViewModel(signInUseCase = get(), getCurrentUserUseCase = get())
    }

    viewModel<SignUpViewModel> {
        SignUpViewModel(signUpUseCase = get())
    }

    viewModel<CarDetailsViewModel> {
        CarDetailsViewModel(
            checkIfCarIsFavoutriteUseCase = get(),
            addToFavouriteUseCase = get(),
            deleteFromFavouriteUseCase = get(),
            getCurrentUserUseCase = get()
        )
    }

    viewModel<FavouriteViewModel> {
        FavouriteViewModel(getFavouritesUseCase = get(), getCurrentUserUseCase = get())
    }

    viewModel<ru.autohub.feature.search.ui.search.SearchViewModel> {
        ru.autohub.feature.search.ui.search.SearchViewModel(
            loadSearchHistoryUseCase = get(),
            updateSearchHistoryUseCase = get(),
            clearSearchHistoryUseCase = get()
        )
    }

    viewModel<ru.autohub.feature.search.ui.searchresults.SearchResultsViewModel> { (screenSwitchable: ScreenSwitchable) ->
        ru.autohub.feature.search.ui.searchresults.SearchResultsViewModel(
            searchCarsByMakeUseCase = get(),
            screenSwitchable = screenSwitchable
        )
    }

    viewModel<SettingsViewModel> {
        SettingsViewModel(
            signOutUseCase = get(),
            getCurrentUserUseCase = get(),
            getAppThemeUseCase = get(),
            changeAppThemeUseCase = get()
        )
    }

    viewModel<MainActivityViewModel> {
        MainActivityViewModel(getAppThemeUseCase = get())
    }
}