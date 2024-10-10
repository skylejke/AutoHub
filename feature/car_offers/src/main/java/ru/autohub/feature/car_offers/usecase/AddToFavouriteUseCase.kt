package ru.autohub.feature.car_offers.usecase

import ru.autohub.core.storage.repository.CarDetailsRepository

class AddToFavouriteUseCase(private val carDetailsRepository: CarDetailsRepository) {
    fun execute(id: String, carMap: HashMap<String, Any>) {
        carDetailsRepository.addToFavourite(id, carMap)
    }
}