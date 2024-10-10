package ru.autohub.feature.search.usecase

import ru.autohub.core.storage.repository.SearchHistoryRepository

class UpdateSearchHistoryUseCase(private val searchHistoryRepository: SearchHistoryRepository) {
    suspend fun execute(query: String) {
        searchHistoryRepository.updateSearchHistory(query)
    }
}