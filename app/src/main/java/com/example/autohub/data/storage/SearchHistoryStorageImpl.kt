package com.example.autohub.data.storage

import android.content.Context
import com.example.autohub.data.storage.model.SearchHistoryDto


private const val SEARCH_HISTORY_PREFERENCES = "SearchHistoryPrefs"
private const val SEARCH_HISTORY_KEY = "searchHistory"

class SearchHistoryStorageImpl(context: Context) : SearchHistoryStorage {

    private val sharedPreferences =
        context.getSharedPreferences(SEARCH_HISTORY_PREFERENCES, Context.MODE_PRIVATE)

    override fun loadSearchHistory(): List<SearchHistoryDto> {
        val searchHistoryPrefs =
            sharedPreferences.getString(SEARCH_HISTORY_KEY, null)?.split(",")
        return searchHistoryPrefs?.map { SearchHistoryDto(it) } ?: emptyList()
    }

    override fun updateSearchHistory(query: String) {
        val updatedList = loadSearchHistory().toMutableList()
        updatedList.add(0, SearchHistoryDto(query))
        if (updatedList.size > 10) {
            updatedList.removeAt(updatedList.lastIndex)
        }
        sharedPreferences.edit().putString(
            SEARCH_HISTORY_KEY,
            updatedList.joinToString(separator = ",") { it.query }).apply()
    }

    override fun clearSearchHistory() {
        sharedPreferences.edit().clear().apply()
    }
}