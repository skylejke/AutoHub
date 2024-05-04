package com.example.autohub.data.repository

import com.example.autohub.data.storage.CarStorage
import com.example.autohub.data.storage.model.Records
import com.example.autohub.domain.model.CarDomain
import com.example.autohub.domain.model.RecordsDomain
import com.example.autohub.domain.repository.CarRepository

class CarRepositoryImpl(private val carStorage: CarStorage) : CarRepository {

    override suspend fun getCars(): RecordsDomain {
        val cars = carStorage.getCars()
        return mapToDomain(cars)
    }

    override suspend fun searchCarsByMake(make: String): RecordsDomain {
        val cars = carStorage.searchCarsByMake(make)
        return mapToDomain(cars)
    }

    private fun mapToDomain(records: Records): RecordsDomain {
        val carList = records.list.map {
            CarDomain(
                bodyType = it.bodyType,
                condition = it.condition,
                displayColor = it.displayColor,
                id = it.id,
                make = it.make,
                mileage = it.mileage,
                mileageUnformatted = it.mileageUnformatted,
                model = it.model,
                photoUrls = it.photoUrls,
                price = it.price,
                priceUnformatted = it.priceUnformatted,
                primaryPhotoUrl = it.primaryPhotoUrl,
                vin = it.vin,
                year = it.year
            )
        }
        return RecordsDomain(list = carList)
    }
}