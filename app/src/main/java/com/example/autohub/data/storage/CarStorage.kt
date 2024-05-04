package com.example.autohub.data.storage

import com.example.autohub.data.storage.model.Records

interface CarStorage {
    suspend fun getCars(): Records

    suspend fun searchCarsByMake(make: String): Records
}