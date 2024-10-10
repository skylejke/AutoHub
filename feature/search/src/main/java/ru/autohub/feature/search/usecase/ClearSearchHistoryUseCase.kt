package ru.autohub.feature.search.usecase

import ru.autohub.core.storage.repository.SearchHistoryRepository

class ClearSearchHistoryUseCase(private val searchHistoryRepository: SearchHistoryRepository) {
    suspend fun execute() {
        searchHistoryRepository.clearSearchHistory()
    }
}