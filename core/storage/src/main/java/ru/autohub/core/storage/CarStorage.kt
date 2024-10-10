package ru.autohub.core.storage

import ru.autohub.core.storage.model.RecordsDto

interface CarStorage {
    suspend fun getCars(): RecordsDto

    suspend fun searchCarsByMake(make: String): RecordsDto

    suspend fun sortCars(sortFilter: String): RecordsDto
}