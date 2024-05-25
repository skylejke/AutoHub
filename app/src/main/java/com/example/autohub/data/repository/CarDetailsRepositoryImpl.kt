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

    override fun addToFavourite(id: String, carMap: HashMap<String, Any>) {
        carDetailsStorage.addToFavourite(id, carMap)
    }

    override fun deleteFromFavourite(id: String) {
        carDetailsStorage.deleteFromFavourite(id)
    }

    private fun mapToStorage(carVo: CarVo): CarDto {
        return CarDto(
            bodyType = carVo.bodyType,
            condition = carVo.condition,
            displayColor = carVo.displayColor,
            id = carVo.id,
            make = carVo.make,
            mileage = carVo.mileage,
            model = carVo.model,
            photoUrls = carVo.photoUrls,
            price = carVo.price,
            primaryPhotoUrl = carVo.primaryPhotoUrl,
            vin = carVo.vin,
            year = carVo.year
        )
    }
}