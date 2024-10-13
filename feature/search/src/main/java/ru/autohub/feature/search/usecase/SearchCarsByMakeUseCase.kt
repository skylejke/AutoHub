package ru.autohub.feature.search.usecase

import ru.autohub.core.storage.repository.CarRepository
import ru.autohub.feature.common.model.RecordsVo
import ru.autohub.feature.common.utils.mapToRecordsVo

internal class SearchCarsByMakeUseCase(private val carRepository: CarRepository) {
    suspend fun execute(make: String): RecordsVo {
        return carRepository.searchCarsByMake(make).mapToRecordsVo()
    }
}