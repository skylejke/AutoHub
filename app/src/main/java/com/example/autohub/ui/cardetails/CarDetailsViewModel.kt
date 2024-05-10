package com.example.autohub.ui.cardetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.autohub.domain.model.CarVo
import com.example.autohub.domain.usecase.CheckIfCarIsFavoutriteUseCase

class CarDetailsViewModel(private val checkIfCarIsFavoutriteUseCase: CheckIfCarIsFavoutriteUseCase) :
    ViewModel() {

    fun checkIfCarIsFavoutrite(carVo: CarVo): LiveData<Boolean> {
        return checkIfCarIsFavoutriteUseCase.execute(carVo)
    }

}