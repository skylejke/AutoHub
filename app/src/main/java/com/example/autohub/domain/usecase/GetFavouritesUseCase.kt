package com.example.autohub.domain.usecase

import androidx.lifecycle.LiveData
import com.example.autohub.domain.model.CarVo
import com.example.autohub.domain.repository.CarRepository

class GetFavouritesUseCase(private val carRepository: CarRepository) {
    fun execute(): LiveData<List<CarVo>> {
        return carRepository.getFavourites()
    }
}