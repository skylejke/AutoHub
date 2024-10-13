package ru.autohub.feature.car_offers.usecase

import ru.autohub.core.storage.CarDetailsStorage

internal class DeleteFromFavouriteUseCase(private val carDetailsRepository: CarDetailsStorage) {
    fun execute(id: String) {
        carDetailsRepository.deleteFromFavourite(id)
    }
}