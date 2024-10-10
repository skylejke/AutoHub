package ru.autohub.core.storage

import ru.autohub.core.storage.model.SearchHistoryDto

interface SearchHistoryStorage {
    suspend fun loadSearchHistory(): List<SearchHistoryDto>

    suspend fun updateSearchHistory(query: String)

    suspend fun clearSearchHistory()
}