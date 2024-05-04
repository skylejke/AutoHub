package com.example.autohub.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.autohub.data.repository.CarRepositoryImpl
import com.example.autohub.data.storage.CarStorageImpl
import com.example.autohub.domain.usecase.GetCarsUseCase
import com.example.autohub.ui.ScreenSwitchable

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(private val screenSwitchable: ScreenSwitchable) :
    ViewModelProvider.Factory {

    private val carStorage by lazy { CarStorageImpl() }

    private val carRepository by lazy { CarRepositoryImpl(carStorage) }

    private val getCarsUseCase by lazy { GetCarsUseCase(carRepository) }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(getCarsUseCase = getCarsUseCase, screenSwitchable) as T
    }
}