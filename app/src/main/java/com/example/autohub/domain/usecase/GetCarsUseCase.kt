package com.example.autohub.domain.usecase

import com.example.autohub.data.repository.CarRepositoryImpl
import com.example.autohub.domain.model.RecordsDomain

class GetCarsUseCase(private val carRepository: CarRepositoryImpl) {

    suspend fun execute(): RecordsDomain {
        return carRepository.getCars()
    }

}