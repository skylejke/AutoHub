package com.example.autohub.data.storage

import androidx.lifecycle.LiveData
import com.example.autohub.data.storage.model.CarDto

interface CarDetailsStorage {
    fun checkIfCarIsFavoutrite(carDto: CarDto): LiveData<Boolean>
    fun addToFavourite(id: String, carMap: HashMap<String, Any>)
    fun deleteFromFavourite(id: String)
}