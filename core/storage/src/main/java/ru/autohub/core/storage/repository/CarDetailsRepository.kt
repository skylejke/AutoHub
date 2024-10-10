package ru.autohub.core.storage.repository

import kotlinx.coroutines.flow.StateFlow
import ru.autohub.core.storage.model.CarDto

interface CarDetailsRepository {
    fun getFavourites(): StateFlow<List<CarDto>>
    fun checkIfCarIsFavoutrite(carVo: CarDto): StateFlow<Boolean>
    fun addToFavourite(id: String, carMap: HashMap<String, Any>)
    fun deleteFromFavourite(id: String)
}