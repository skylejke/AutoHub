package com.example.autohub.domain.repository

import androidx.lifecycle.LiveData
import com.example.autohub.domain.model.CarVo

interface CarDetailsRepository {
    fun checkIfCarIsFavoutrite(carVo: CarVo):LiveData<Boolean>
}