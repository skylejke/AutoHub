package com.example.autohub.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.autohub.domain.model.RecordsDomain
import com.example.autohub.domain.usecase.SearchCarsByMakeUseCase
import com.example.autohub.ui.ScreenSwitchable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchResultsViewModel(
    private val searchCarsByMakeUseCase: SearchCarsByMakeUseCase,
    private val screenSwitchable: ScreenSwitchable
) : ViewModel() {

    private var _carsLiveData = MutableLiveData<RecordsDomain>()
    val carsLiveData: LiveData<RecordsDomain> = _carsLiveData

    suspend fun get(query: String) {
        withContext(Dispatchers.Main) {
            screenSwitchable.showProgressBar()
        }
        try {
            val records: RecordsDomain = searchCarsByMakeUseCase.execute(query)
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
                _carsLiveData.value = RecordsDomain(emptyList())
                screenSwitchable.showError()
            }
        } finally {
            withContext(Dispatchers.Main) {
                screenSwitchable.hideProgressBar()
            }
        }
    }
}