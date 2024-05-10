package com.example.autohub.ui.cardetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.autohub.data.repository.CarDetailsRepositoryImpl
import com.example.autohub.data.storage.CarDetailsStorageImpl
import com.example.autohub.domain.usecase.CheckIfCarIsFavoutriteUseCase

@Suppress("UNCHECKED_CAST")
class CarDetailsViewModelFactory: ViewModelProvider.Factory {

    private val carDetailsStorage by lazy { CarDetailsStorageImpl() }

    private val carDetailsRepository by lazy { CarDetailsRepositoryImpl(carDetailsStorage) }

    private val checkIfCarIsFavoutriteUseCase by lazy { CheckIfCarIsFavoutriteUseCase(carDetailsRepository) }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CarDetailsViewModel(checkIfCarIsFavoutriteUseCase = checkIfCarIsFavoutriteUseCase) as T
    }
}