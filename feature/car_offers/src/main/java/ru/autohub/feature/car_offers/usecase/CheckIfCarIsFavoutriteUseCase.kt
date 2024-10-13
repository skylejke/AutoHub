package ru.autohub.feature.car_offers.usecase

import kotlinx.coroutines.flow.StateFlow
import ru.autohub.core.storage.repository.CarDetailsRepository
import ru.autohub.feature.common.model.CarVo
import ru.autohub.feature.common.utils.mapToCarDto

internal class CheckIfCarIsFavoutriteUseCase(private val carDetailsRepository: CarDetailsRepository) {
    fun execute(carVo: CarVo): StateFlow<Boolean>{
        return carDetailsRepository.checkIfCarIsFavoutrite(carVo.mapToCarDto())
    }
}