package com.example.autohub.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.autohub.domain.model.RecordsVo
import com.example.autohub.domain.usecase.GetCarsUseCase
import com.example.autohub.ui.ScreenSwitchable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val getCarsUseCase: GetCarsUseCase,
    private val screenSwitchable: ScreenSwitchable
) : ViewModel() {

    private val _carsLiveData = MutableLiveData<RecordsVo>()
    val carsLiveData: LiveData<RecordsVo> = _carsLiveData

    suspend fun get() {
        withContext(Dispatchers.Main) {
            screenSwitchable.showProgressBar()
        }
        try {
            val records: RecordsVo = getCarsUseCase.execute()
            _carsLiveData.value = records
            if (records.list.isEmpty()) {
                withContext(Dispatchers.Main) {
                    screenSwitchable.hideError()
                    screenSwitchable.showNoData()
                }
            } else {
                withContext(Dispatchers.Main) {
                    screenSwitchable.hideError()
                    screenSwitchable.showData()
                }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                _carsLiveData.value = RecordsVo(emptyList())
                screenSwitchable.showError()
            }
        } finally {
            withContext(Dispatchers.Main) {
                screenSwitchable.hideProgressBar()
            }
        }
    }
}