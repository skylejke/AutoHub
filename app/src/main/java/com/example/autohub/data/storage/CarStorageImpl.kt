package com.example.autohub.data.storage

import com.example.autohub.data.api.RetrofitCarInstance
import com.example.autohub.data.storage.model.RecordsDto

class CarStorageImpl : CarStorage {

    override suspend fun getCars(): RecordsDto {
        return RetrofitCarInstance.api.getCars().body() ?: RecordsDto(list = emptyList())
    }

    override suspend fun searchCarsByMake(make: String): RecordsDto {
        return RetrofitCarInstance.api.searchCarsByMake(make).body() ?: RecordsDto(list = emptyList())
    }

}