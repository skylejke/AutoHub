package com.example.autohub.domain.usecase

import androidx.lifecycle.LiveData
import com.example.autohub.domain.model.CarVo
import com.example.autohub.domain.repository.CarDetailsRepository

class GetFavouritesUseCase(private val carDetailsRepository: CarDetailsRepository) {
    fun execute(): LiveData<List<CarVo>> {
        return carDetailsRepository.getFavourites()
    }
}