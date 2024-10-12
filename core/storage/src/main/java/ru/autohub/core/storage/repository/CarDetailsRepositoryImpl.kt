package ru.autohub.core.storage.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ru.autohub.core.storage.CarDetailsStorage
import ru.autohub.core.storage.model.CarDto

internal class CarDetailsRepositoryImpl(private val carDetailsStorage: CarDetailsStorage) :
    CarDetailsRepository {

    override fun getFavourites(): StateFlow<List<CarDto>> {
        return carDetailsStorage.getFavourites().map { mapToDomainFavourites(it) }
            .stateIn(
                scope = CoroutineScope(Dispatchers.IO),
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )
    }

    override fun checkIfCarIsFavoutrite(carVo: CarDto): StateFlow<Boolean> {
        return carDetailsStorage.checkIfCarIsFavoutrite(carVo)
    }

    override fun addToFavourite(id: String, carMap: HashMap<String, Any>) {
        carDetailsStorage.addToFavourite(id, carMap)
    }

    override fun deleteFromFavourite(id: String) {
        carDetailsStorage.deleteFromFavourite(id)
    }

    private fun mapToStorage(carVo: CarDto): CarDto {
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

    private fun mapToDomainFavourites(carsDto: List<CarDto>): List<CarDto> {
        val carList = carsDto.map { car ->
            CarDto(
                bodyType = car.bodyType,
                condition = car.condition,
                displayColor = car.displayColor ?: "",
                id = car.id,
                make = car.make,
                mileage = car.mileage,
                model = car.model,
                photoUrls = car.photoUrls,
                price = car.price,
                primaryPhotoUrl = car.primaryPhotoUrl,
                vin = car.vin,
                year = car.year
            )
        }
        return carList
    }
}