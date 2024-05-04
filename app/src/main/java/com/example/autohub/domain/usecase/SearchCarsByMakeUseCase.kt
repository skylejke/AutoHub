package com.example.autohub.domain.usecase

import com.example.autohub.data.repository.CarRepositoryImpl
import com.example.autohub.domain.model.RecordsDomain

class SearchCarsByMakeUseCase(private val carRepository: CarRepositoryImpl) {

    suspend fun execute(make: String): RecordsDomain {
        return carRepository.searchCarsByMake(make)
    }

}