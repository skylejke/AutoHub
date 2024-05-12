package com.example.autohub.ui.search.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.autohub.domain.model.SearchHistoryVo
import com.example.autohub.domain.usecase.ClearSearchHistoryUseCase
import com.example.autohub.domain.usecase.LoadSearchHistoryUseCase
import com.example.autohub.domain.usecase.UpdateSearchHistoryUseCase

class SearchViewModel(
    private val loadSearchHistoryUseCase: LoadSearchHistoryUseCase,
    private val updateSearchHistoryUseCase: UpdateSearchHistoryUseCase,
    private val clearSearchHistoryUseCase: ClearSearchHistoryUseCase
) : ViewModel() {

    private val _searchHistory = MutableLiveData<List<SearchHistoryVo>>()
    val searchHistory: LiveData<List<SearchHistoryVo>> = _searchHistory

    fun loadSearchHistory() {
        _searchHistory.value = loadSearchHistoryUseCase.execute()
    }

    fun updateSearchHistory(query: String) {
        updateSearchHistoryUseCase.execute(query)
        loadSearchHistory() // Reload search history after update
    }

    fun clearSearchHistory() {
        clearSearchHistoryUseCase.execute()
        _searchHistory.value = emptyList() // Clear LiveData
    }
}
