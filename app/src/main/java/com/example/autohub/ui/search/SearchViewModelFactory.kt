package com.example.autohub.ui.search

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.autohub.data.repository.SearchHistoryRepositoryImpl
import com.example.autohub.data.storage.SearchHistoryStorageImpl
import com.example.autohub.domain.usecase.ClearSearchHistoryUseCase
import com.example.autohub.domain.usecase.LoadSearchHistoryUseCase
import com.example.autohub.domain.usecase.UpdateSearchHistoryUseCase

@Suppress("UNCHECKED_CAST")
class SearchViewModelFactory(context: Context) : ViewModelProvider.Factory {
    private val searchHistoryStorage by lazy { SearchHistoryStorageImpl(context = context) }

    private val searchHistoryRepository by lazy { SearchHistoryRepositoryImpl(searchHistoryStorage) }

    private val loadSearchHistoryUseCase by lazy { LoadSearchHistoryUseCase(searchHistoryRepository = searchHistoryRepository) }

    private val updateSearchHistoryUseCase by lazy {
        UpdateSearchHistoryUseCase(
            searchHistoryRepository = searchHistoryRepository
        )
    }

    private val clearSearchHistoryUseCase by lazy {
        ClearSearchHistoryUseCase(
            searchHistoryRepository = searchHistoryRepository
        )
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchViewModel(
            loadSearchHistoryUseCase = loadSearchHistoryUseCase,
            updateSearchHistoryUseCase = updateSearchHistoryUseCase,
            clearSearchHistoryUseCase = clearSearchHistoryUseCase
        ) as T
    }
}