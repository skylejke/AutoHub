package com.example.autohub.domain.repository

import androidx.lifecycle.LiveData
import com.example.autohub.domain.model.CarVo
import com.example.autohub.domain.model.RecordsVo

interface CarRepository {
    suspend fun getCars(): RecordsVo

    suspend fun searchCarsByMake(make: String): RecordsVo

    fun getFavourites(): LiveData<List<CarVo>>
}