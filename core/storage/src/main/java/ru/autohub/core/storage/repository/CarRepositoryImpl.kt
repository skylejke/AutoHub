package ru.autohub.core.storage.repository

import ru.autohub.core.storage.CarStorage
import ru.autohub.core.storage.model.RecordsDto

internal class CarRepositoryImpl(private val carStorage: CarStorage) : CarRepository {

    override suspend fun getCars(): RecordsDto {
        return carStorage.getCars()
    }

    override suspend fun searchCarsByMake(make: String): RecordsDto {
        return carStorage.searchCarsByMake(make)
    }

    override suspend fun sortCars(sortFilter: String): RecordsDto {
        return carStorage.sortCars(sortFilter)
    }
}