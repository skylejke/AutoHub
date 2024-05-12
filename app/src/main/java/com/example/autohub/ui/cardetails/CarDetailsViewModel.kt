package com.example.autohub.ui.cardetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.autohub.domain.model.CarVo
import com.example.autohub.domain.usecase.AddToFavouriteUseCase
import com.example.autohub.domain.usecase.CheckIfCarIsFavoutriteUseCase
import com.example.autohub.domain.usecase.DeleteFromFavouriteUseCase

class CarDetailsViewModel(
    private val checkIfCarIsFavoutriteUseCase: CheckIfCarIsFavoutriteUseCase,
    private val addToFavouriteUseCase: AddToFavouriteUseCase,
    private val deleteFromFavouriteUseCase: DeleteFromFavouriteUseCase
) :
    ViewModel() {

    fun checkIfCarIsFavoutrite(carVo: CarVo): LiveData<Boolean> {
        return checkIfCarIsFavoutriteUseCase.execute(carVo)
    }

    fun addToFavourite(id: String, carMap: HashMap<String, Any>) {
        addToFavouriteUseCase.execute(id, carMap)
    }

    fun deleteFromFavourite(id: String){
        deleteFromFavouriteUseCase.execute(id)
    }

}