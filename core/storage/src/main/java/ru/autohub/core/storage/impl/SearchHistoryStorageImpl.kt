package ru.autohub.core.storage.impl

import android.content.Context
import ru.autohub.core.storage.database.DataBase
import ru.autohub.core.storage.model.SearchHistoryDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.autohub.core.storage.SearchHistoryStorage

internal class SearchHistoryStorageImpl(context: Context) : SearchHistoryStorage {

    private val database: DataBase = DataBase.getDataBase(context)

    override suspend fun loadSearchHistory(): List<SearchHistoryDto> {
        return withContext(Dispatchers.IO) {
            database.getDao().getSearchHistory()
        }
    }

    override suspend fun updateSearchHistory(query: String) {
        withContext(Dispatchers.IO) {
            val searchHistoryDto = SearchHistoryDto(null, query)

            val searchHistory = loadSearchHistory()
            val updatedList = mutableListOf<SearchHistoryDto>()

            updatedList.add(0, searchHistoryDto)

            updatedList.addAll(searchHistory)

            if (updatedList.size > 10) {
                updatedList.subList(10, updatedList.size).clear()
            }

            database.getDao().clearSearchHistory()

            updatedList.forEach { item ->
                database.getDao().insertSearchHistoryItem(item)
            }
        }
    }


    override suspend fun clearSearchHistory() {
        withContext(Dispatchers.IO) {
            database.getDao().clearSearchHistory()
        }
    }

}