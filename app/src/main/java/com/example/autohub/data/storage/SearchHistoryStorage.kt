package com.example.autohub.data.storage

import com.example.autohub.data.storage.model.SearchHistoryDto

interface SearchHistoryStorage {
    fun loadSearchHistory(): List<SearchHistoryDto>

    fun updateSearchHistory(query: String)

    fun clearSearchHistory()
}