package ru.autohub.feature.search.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.autohub.feature.common.utils.ScreenSwitchable
import ru.autohub.feature.search.ui.search.SearchViewModel
import ru.autohub.feature.search.ui.searchresults.SearchResultsViewModel
import ru.autohub.feature.search.usecase.ClearSearchHistoryUseCase
import ru.autohub.feature.search.usecase.LoadSearchHistoryUseCase
import ru.autohub.feature.search.usecase.SearchCarsByMakeUseCase
import ru.autohub.feature.search.usecase.UpdateSearchHistoryUseCase

val searchModule = module {

    factory<ClearSearchHistoryUseCase> {
        ClearSearchHistoryUseCase(searchHistoryRepository = get())
    }

    factory<LoadSearchHistoryUseCase> {
        LoadSearchHistoryUseCase(searchHistoryRepository = get())
    }

    factory<SearchCarsByMakeUseCase> {
        SearchCarsByMakeUseCase(carRepository = get())
    }

    factory<UpdateSearchHistoryUseCase> {
        UpdateSearchHistoryUseCase(searchHistoryRepository = get())
    }

    viewModel<SearchViewModel> {
        SearchViewModel(
            loadSearchHistoryUseCase = get(),
            updateSearchHistoryUseCase = get(),
            clearSearchHistoryUseCase = get()
        )
    }

    viewModel<SearchResultsViewModel> { (screenSwitchable: ScreenSwitchable) ->
        SearchResultsViewModel(
            searchCarsByMakeUseCase = get(),
            screenSwitchable = screenSwitchable
        )
    }
}