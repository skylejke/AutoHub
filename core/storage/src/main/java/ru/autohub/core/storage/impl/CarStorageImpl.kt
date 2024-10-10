package ru.autohub.core.storage.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.autohub.core.storage.CarStorage
import ru.autohub.core.storage.api.RetrofitCarInstance
import ru.autohub.core.storage.model.RecordsDto

internal class CarStorageImpl : CarStorage {

    override suspend fun getCars(): RecordsDto {
        return withContext(Dispatchers.IO) {
            RetrofitCarInstance.api.getCars().body() ?: RecordsDto(list = emptyList())
        }
    }

    override suspend fun searchCarsByMake(make: String): RecordsDto {
        return withContext(Dispatchers.IO) {
            RetrofitCarInstance.api.searchCarsByMake(make).body()
                ?: RecordsDto(list = emptyList())
        }
    }

    override suspend fun sortCars(sortFilter: String): RecordsDto {
        return withContext(Dispatchers.IO) {
            RetrofitCarInstance.api.sortCars(sortFilter).body()
                ?: RecordsDto(list = emptyList())
        }
    }
}