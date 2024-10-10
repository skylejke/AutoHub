package ru.autohub.core.storage.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.autohub.core.storage.model.SearchHistoryDto

@Dao
interface Dao {
    @Insert
    fun insertSearchHistoryItem(searchHistoryDto: SearchHistoryDto)
    @Query("SELECT * FROM searchHistory ORDER BY id DESC")
    fun getSearchHistory(): List<SearchHistoryDto>

    @Query("DELETE FROM searchHistory")
    fun clearSearchHistory()
}