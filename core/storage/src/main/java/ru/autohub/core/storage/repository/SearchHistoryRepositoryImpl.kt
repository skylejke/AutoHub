package ru.autohub.core.storage.repository

import ru.autohub.core.storage.SearchHistoryStorage
import ru.autohub.core.storage.model.SearchHistoryDto

internal class SearchHistoryRepositoryImpl(private val searchHistoryStorage: SearchHistoryStorage) :
    SearchHistoryRepository {

    override suspend fun loadSearchHistory(): List<SearchHistoryDto> =
        searchHistoryStorage.loadSearchHistory().map { searchHistoryItem ->
            SearchHistoryDto(
                query = searchHistoryItem.query
            )
        }

    override suspend fun updateSearchHistory(query: String) {
        searchHistoryStorage.updateSearchHistory(query)
    }

    override suspend fun clearSearchHistory() {
        searchHistoryStorage.clearSearchHistory()
    }
}