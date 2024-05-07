package com.example.autohub.data.repository

import com.example.autohub.data.storage.SearchHistoryStorage
import com.example.autohub.data.storage.model.SearchHistoryDto
import com.example.autohub.domain.model.SearchHistoryVo
import com.example.autohub.domain.repository.SearchHistoryRepository

class SearchHistoryRepositoryImpl(private val searchHistoryStorage: SearchHistoryStorage) :
    SearchHistoryRepository {

    override fun loadSearchHistory(): List<SearchHistoryVo> {
        val searchHistory = searchHistoryStorage.loadSearchHistory()
        return mapToDomain(searchHistory)
    }

    override fun updateSearchHistory(query: String) {
        searchHistoryStorage.updateSearchHistory(query)
    }

    override fun clearSearchHistory() {
        searchHistoryStorage.clearSearchHistory()
    }

    private fun mapToDomain(searchHistoryDtoList: List<SearchHistoryDto>): List<SearchHistoryVo> {
        val searchHistoryList = searchHistoryDtoList.map { searchHistoryItem ->
            SearchHistoryVo(
                query = searchHistoryItem.query
            )
        }
        return searchHistoryList
    }

}