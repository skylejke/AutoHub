package ru.autohub.feature.search.usecase

import android.util.Log
import ru.autohub.core.storage.repository.SearchHistoryRepository
import ru.autohub.feature.search.model.SearchHistoryVo
import ru.autohub.feature.search.utils.mapToSearchHistoryVo

internal class LoadSearchHistoryUseCase(private val searchHistoryRepository: SearchHistoryRepository) {
    suspend fun execute(): List<SearchHistoryVo> {
        Log.d("ddd", "use case ${searchHistoryRepository.loadSearchHistory()}")
        return searchHistoryRepository.loadSearchHistory().map {
            it.mapToSearchHistoryVo()
        }
    }
}