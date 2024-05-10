package com.example.autohub.domain.usecase

import androidx.lifecycle.LiveData
import com.example.autohub.domain.model.CarVo
import com.example.autohub.domain.repository.CarDetailsRepository

class CheckIfCarIsFavoutriteUseCase(private val carDetailsRepository: CarDetailsRepository) {
    fun execute(carVo: CarVo): LiveData<Boolean>{
        return carDetailsRepository.checkIfCarIsFavoutrite(carVo)
    }
}