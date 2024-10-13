package ru.autohub.feature.car_offers.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.autohub.feature.car_offers.ui.cardetails.CarDetailsViewModel
import ru.autohub.feature.car_offers.ui.favourite.FavouriteViewModel
import ru.autohub.feature.car_offers.ui.home.HomeViewModel
import ru.autohub.feature.car_offers.usecase.AddToFavouriteUseCase
import ru.autohub.feature.car_offers.usecase.CheckIfCarIsFavoutriteUseCase
import ru.autohub.feature.car_offers.usecase.DeleteFromFavouriteUseCase
import ru.autohub.feature.car_offers.usecase.GetCarsUseCase
import ru.autohub.feature.car_offers.usecase.GetFavouritesUseCase
import ru.autohub.feature.car_offers.usecase.SortCarsUseCase

val carOffersModule = module {

    factory<AddToFavouriteUseCase> {
        AddToFavouriteUseCase(carDetailsRepository = get())
    }

    factory<CheckIfCarIsFavoutriteUseCase> {
        CheckIfCarIsFavoutriteUseCase(carDetailsRepository = get())
    }

    factory<DeleteFromFavouriteUseCase> {
        DeleteFromFavouriteUseCase(carDetailsRepository = get())
    }

    factory<GetCarsUseCase> {
        GetCarsUseCase(carRepository = get())
    }

    factory<GetFavouritesUseCase> {
        GetFavouritesUseCase(carDetailsRepository = get())
    }

    factory<SortCarsUseCase> {
        SortCarsUseCase(carRepository = get())
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

    viewModel<HomeViewModel> {
        HomeViewModel(
            getCarsUseCase = get(),
            sortCarsUseCase = get()
        )
    }
}