package ru.autohub.feature.car_offers.usecase

import ru.autohub.core.storage.repository.CarRepository
import ru.autohub.feature.common.model.RecordsVo
import ru.autohub.feature.common.utils.mapToRecordsVo

class GetCarsUseCase(private val carRepository: CarRepository) {

    suspend fun execute(): RecordsVo {
        return carRepository.getCars().mapToRecordsVo()
    }
}