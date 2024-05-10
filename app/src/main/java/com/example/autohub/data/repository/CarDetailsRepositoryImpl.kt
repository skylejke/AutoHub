package com.example.autohub.data.repository

import androidx.lifecycle.LiveData
import com.example.autohub.data.storage.CarDetailsStorage
import com.example.autohub.data.storage.model.CarDto
import com.example.autohub.domain.model.CarVo
import com.example.autohub.domain.repository.CarDetailsRepository

class CarDetailsRepositoryImpl(private val carDetailsStorage: CarDetailsStorage) :
    CarDetailsRepository {

    override fun checkIfCarIsFavoutrite(carVo: CarVo): LiveData<Boolean> {
        return carDetailsStorage.checkIfCarIsFavoutrite(mapToStorage(carVo))
    }

    private fun mapToDomain(carDto: CarDto): CarVo {
        return CarVo(
            bodyType = carDto.bodyType,
            condition = carDto.condition,
            displayColor = carDto.displayColor,
            id = carDto.id,
            make = carDto.make,
            mileage = carDto.mileage,
            mileageUnformatted = carDto.mileageUnformatted,
            model = carDto.model,
            photoUrls = carDto.photoUrls,
            price = carDto.price,
            priceUnformatted = carDto.priceUnformatted,
            primaryPhotoUrl = carDto.primaryPhotoUrl,
            vin = carDto.vin,
            year = carDto.year
        )
    }

    private fun mapToStorage(carVo: CarVo): CarDto {
        return CarDto(
            bodyType = carVo.bodyType,
            condition = carVo.condition,
            displayColor = carVo.displayColor,
            id = carVo.id,
            make = carVo.make,
            mileage = carVo.mileage,
            mileageUnformatted = carVo.mileageUnformatted,
            model = carVo.model,
            photoUrls = carVo.photoUrls,
            price = carVo.price,
            priceUnformatted = carVo.priceUnformatted,
            primaryPhotoUrl = carVo.primaryPhotoUrl,
            vin = carVo.vin,
            year = carVo.year
        )
    }
}