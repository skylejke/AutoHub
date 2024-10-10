package ru.autohub.core.storage

import ru.autohub.core.storage.model.CarDto
import kotlinx.coroutines.flow.StateFlow

interface CarDetailsStorage {
    fun getFavourites(): StateFlow<List<CarDto>>
    fun checkIfCarIsFavoutrite(carDto: CarDto): StateFlow<Boolean>
    fun addToFavourite(id: String, carMap: HashMap<String, Any>)
    fun deleteFromFavourite(id: String)
}