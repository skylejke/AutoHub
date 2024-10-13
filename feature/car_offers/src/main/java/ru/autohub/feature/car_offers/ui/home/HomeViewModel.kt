package ru.autohub.feature.car_offers.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.autohub.feature.car_offers.usecase.GetCarsUseCase
import ru.autohub.feature.car_offers.usecase.SortCarsUseCase
import ru.autohub.feature.common.model.RecordsVo

internal class HomeViewModel(
    private val getCarsUseCase: GetCarsUseCase,
    private val sortCarsUseCase: SortCarsUseCase,
) : ViewModel() {

    private val _carsStateFlow = MutableStateFlow(RecordsVo(emptyList()))
    val carsStateFlow: StateFlow<RecordsVo> = _carsStateFlow

    fun get() {
        viewModelScope.launch {
            try {
                val records: RecordsVo = getCarsUseCase.execute()
                _carsStateFlow.value = records
            } catch (e: Exception) {
                Log.e("ApiError", e.message.toString())
                _carsStateFlow.value = RecordsVo(emptyList())
            }
        }
    }

    fun sort(sortFilter: String) {
        viewModelScope.launch {
            try {
                val records: RecordsVo = sortCarsUseCase.execute(sortFilter)
                _carsStateFlow.value = records
            } catch (e: Exception) {
                Log.e("ApiError", e.message.toString())
                _carsStateFlow.value = RecordsVo(emptyList())
            }
        }
    }
}
