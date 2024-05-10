package com.example.autohub.ui.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.autohub.domain.model.CarVo
import com.example.autohub.domain.usecase.GetFavouritesUseCase

class FavouriteViewModel(private val getFavouritesUseCase: GetFavouritesUseCase): ViewModel() {
    val carsLiveData: LiveData<List<CarVo>> = getFavouritesUseCase.execute()
}