package com.example.autohub.domain.repository

import com.example.autohub.domain.model.SearchHistoryVo

interface SearchHistoryRepository {
    fun loadSearchHistory(): List<SearchHistoryVo>

    fun updateSearchHistory(query: String)

    fun clearSearchHistory()
}