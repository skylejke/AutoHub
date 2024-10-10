package ru.autohub.feature.search.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.autohub.feature.search.model.SearchHistoryVo
import ru.autohub.feature.search.usecase.ClearSearchHistoryUseCase
import ru.autohub.feature.search.usecase.LoadSearchHistoryUseCase
import ru.autohub.feature.search.usecase.UpdateSearchHistoryUseCase

class SearchViewModel(
    private val loadSearchHistoryUseCase: LoadSearchHistoryUseCase,
    private val updateSearchHistoryUseCase: UpdateSearchHistoryUseCase,
    private val clearSearchHistoryUseCase: ClearSearchHistoryUseCase
) : ViewModel() {

    private val _searchHistory = MutableStateFlow<List<SearchHistoryVo>>(emptyList())
    val searchHistory: StateFlow<List<SearchHistoryVo>> = _searchHistory

    suspend fun loadSearchHistory() {
        _searchHistory.value = loadSearchHistoryUseCase.execute()
        Log.d("ddd", "view model ${loadSearchHistoryUseCase.execute()}")
    }

    suspend fun updateSearchHistory(query: String) {
        updateSearchHistoryUseCase.execute(query)
        loadSearchHistory()
    }

    suspend fun clearSearchHistory() {
        clearSearchHistoryUseCase.execute()
        _searchHistory.value = emptyList()
    }
}
