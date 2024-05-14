package com.example.autohub.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.autohub.data.storage.CarStorage
import com.example.autohub.data.storage.model.CarDto
import com.example.autohub.data.storage.model.RecordsDto
import com.example.autohub.domain.model.CarVo
import com.example.autohub.domain.model.RecordsVo
import com.example.autohub.domain.repository.CarRepository

class CarRepositoryImpl(private val carStorage: CarStorage) : CarRepository {

    override suspend fun getCars(): RecordsVo {
        return mapToDomain(carStorage.getCars())
    }

    override suspend fun searchCarsByMake(make: String): RecordsVo {
        return mapToDomain(carStorage.searchCarsByMake(make))
    }

    override fun getFavourites(): LiveData<List<CarVo>> {
        return carStorage.getFavourites().map { mapToDomainFavourites(it) }
    }

    override suspend fun sortCars(sortFilter: String): RecordsVo {
        return mapToDomain(carStorage.sortCars(sortFilter))
    }

    private fun mapToDomain(recordsDto: RecordsDto): RecordsVo {
        val carList = recordsDto.list.map { car ->
            CarVo(
                bodyType = car.bodyType,
                condition = car.condition,
                displayColor = car.displayColor,
                id = car.id,
                make = car.make,
                mileage = car.mileage,
                mileageUnformatted = car.mileageUnformatted,
                model = car.model,
                photoUrls = car.photoUrls,
                price = car.price,
                priceUnformatted = car.priceUnformatted,
                primaryPhotoUrl = car.primaryPhotoUrl,
                vin = car.vin,
                year = car.year
            )
        }
        return RecordsVo(list = carList)
    }

    private fun mapToDomainFavourites(carsDto: List<CarDto>): List<CarVo> {
        val carList = carsDto.map { car ->
            CarVo(
                bodyType = car.bodyType,
                condition = car.condition,
                displayColor = car.displayColor,
                id = car.id,
                make = car.make,
                mileage = car.mileage,
                mileageUnformatted = car.mileageUnformatted,
                model = car.model,
                photoUrls = car.photoUrls,
                price = car.price,
                priceUnformatted = car.priceUnformatted,
                primaryPhotoUrl = car.primaryPhotoUrl,
                vin = car.vin,
                year = car.year
            )
        }
        return carList
    }
}