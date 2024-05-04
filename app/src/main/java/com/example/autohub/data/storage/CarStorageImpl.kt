package com.example.autohub.data.storage

import com.example.autohub.data.api.RetrofitCarInstance
import com.example.autohub.data.storage.model.Records

class CarStorageImpl : CarStorage {

    override suspend fun getCars(): Records {
        return RetrofitCarInstance.api.getCars().body() ?: Records(list = emptyList())
    }

    override suspend fun searchCarsByMake(make: String): Records {
        return RetrofitCarInstance.api.searchCarsByMake(make).body() ?: Records(list = emptyList())
    }

}