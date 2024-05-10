package com.example.autohub.ui.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.autohub.data.repository.CarRepositoryImpl
import com.example.autohub.data.storage.CarStorageImpl
import com.example.autohub.domain.usecase.GetFavouritesUseCase

@Suppress("UNCHECKED_CAST")
class FavouriteViewModelFactory: ViewModelProvider.Factory {

    private val carStorage by lazy { CarStorageImpl() }

    private val carRepository by lazy { CarRepositoryImpl(carStorage) }

    private val getFavouritesUseCase by lazy { GetFavouritesUseCase(carRepository) }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavouriteViewModel(getFavouritesUseCase = getFavouritesUseCase) as T
    }
}