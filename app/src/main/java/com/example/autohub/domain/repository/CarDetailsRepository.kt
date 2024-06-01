package com.example.autohub.domain.repository

import androidx.lifecycle.LiveData
import com.example.autohub.domain.model.CarVo

interface CarDetailsRepository {
    fun getFavourites(): LiveData<List<CarVo>>
    fun checkIfCarIsFavoutrite(carVo: CarVo):LiveData<Boolean>
    fun addToFavourite(id: String, carMap: HashMap<String, Any>)
    fun deleteFromFavourite(id: String)
}