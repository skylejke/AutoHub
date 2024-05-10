package com.example.autohub.data.storage

import androidx.lifecycle.LiveData
import com.example.autohub.data.storage.model.CarDto
import com.example.autohub.data.storage.model.RecordsDto

interface CarStorage {
    suspend fun getCars(): RecordsDto

    suspend fun searchCarsByMake(make: String): RecordsDto

    fun getFavourites(): LiveData<List<CarDto>>
}