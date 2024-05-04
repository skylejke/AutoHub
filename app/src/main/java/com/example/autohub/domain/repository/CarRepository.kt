package com.example.autohub.domain.repository

import com.example.autohub.domain.model.RecordsDomain

interface CarRepository {
    suspend fun getCars(): RecordsDomain
    suspend fun searchCarsByMake(make: String): RecordsDomain
}