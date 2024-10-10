package ru.autohub.core.storage.repository

import ru.autohub.core.storage.model.SearchHistoryDto

interface SearchHistoryRepository {
    suspend fun loadSearchHistory(): List<SearchHistoryDto>

    suspend fun updateSearchHistory(query: String)

    suspend fun clearSearchHistory()
}