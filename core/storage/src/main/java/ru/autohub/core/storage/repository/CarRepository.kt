package ru.autohub.core.storage.repository

import ru.autohub.core.storage.model.RecordsDto

interface CarRepository {
    suspend fun getCars(): RecordsDto

    suspend fun searchCarsByMake(make: String): RecordsDto

    suspend fun sortCars(sortFilter: String): RecordsDto
}