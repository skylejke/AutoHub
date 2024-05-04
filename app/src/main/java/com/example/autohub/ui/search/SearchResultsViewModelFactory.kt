package com.example.autohub.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.autohub.data.repository.CarRepositoryImpl
import com.example.autohub.data.storage.CarStorageImpl
import com.example.autohub.domain.usecase.SearchCarsByMakeUseCase
import com.example.autohub.ui.ScreenSwitchable

@Suppress("UNCHECKED_CAST")
class SearchResultsViewModelFactory(private val screenSwitchable: ScreenSwitchable) :
    ViewModelProvider.Factory {

    private val carStorage by lazy { CarStorageImpl() }

    private val carRepository by lazy { CarRepositoryImpl(carStorage) }

    private val searchCarsByMakeUseCase by lazy { SearchCarsByMakeUseCase(carRepository) }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchResultsViewModel(
            searchCarsByMakeUseCase = searchCarsByMakeUseCase,
            screenSwitchable
        ) as T
    }
}