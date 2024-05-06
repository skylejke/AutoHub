package com.example.autohub.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.autohub.domain.model.RecordsVo
import com.example.autohub.domain.usecase.SearchCarsByMakeUseCase
import com.example.autohub.ui.ScreenSwitchable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchResultsViewModel(
    private val searchCarsByMakeUseCase: SearchCarsByMakeUseCase,
    private val screenSwitchable: ScreenSwitchable
) : ViewModel() {

    private var _carsLiveData = MutableLiveData<RecordsVo>()
    val carsLiveData: LiveData<RecordsVo> = _carsLiveData

    suspend fun get(query: String) {
        withContext(Dispatchers.Main) {
            screenSwitchable.showProgressBar()
        }
        try {
            val records: RecordsVo = searchCarsByMakeUseCase.execute(query)
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