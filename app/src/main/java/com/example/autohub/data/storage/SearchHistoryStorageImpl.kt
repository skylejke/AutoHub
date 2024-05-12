package com.example.autohub.data.storage

import android.content.Context
import com.example.autohub.data.database.DataBase
import com.example.autohub.data.storage.model.SearchHistoryDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class SearchHistoryStorageImpl(context: Context) : SearchHistoryStorage {

    private val database = DataBase.getDataBase(context)

    override fun loadSearchHistory(): List<SearchHistoryDto> {
        return runBlocking {
            withContext(Dispatchers.IO) {
                database.getDao().getSearchHistory()
            }
        }
    }

    override fun updateSearchHistory(query: String) {
        runBlocking {
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
    }


    override fun clearSearchHistory() {
        runBlocking {
            withContext(Dispatchers.IO) {
                database.getDao().clearSearchHistory()
            }
        }
    }

}