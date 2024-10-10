package ru.autohub.feature.car_offers.usecase

import ru.autohub.core.storage.repository.CarRepository
import ru.autohub.feature.common.model.RecordsVo
import ru.autohub.feature.common.utils.mapToRecordsVo

class SortCarsUseCase(private val carRepository: CarRepository) {
    suspend fun execute(sortFilter: String): RecordsVo {
        return carRepository.sortCars(sortFilter).mapToRecordsVo()
    }
}