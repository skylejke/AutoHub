package ru.autohub.core.storage.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.autohub.core.storage.model.SearchHistoryDto

const val DATABASE_NAME = "autohub.db"
@Database (entities = [SearchHistoryDto::class], version = 1)
abstract class DataBase : RoomDatabase() {

    abstract fun getDao(): Dao

    companion object {
        fun getDataBase(context: Context): DataBase {
            return Room.databaseBuilder(context, DataBase::class.java, DATABASE_NAME).build()
        }
    }
}