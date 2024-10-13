package ru.autohub.feature.search.ui.searchresults

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import ru.autohub.feature.common.model.RecordsVo
import ru.autohub.feature.common.utils.ScreenSwitchable
import ru.autohub.feature.common.utils.withMainContext
import ru.autohub.feature.search.usecase.SearchCarsByMakeUseCase

internal class SearchResultsViewModel(
    private val searchCarsByMakeUseCase: SearchCarsByMakeUseCase,
    private val screenSwitchable: ScreenSwitchable
) : ViewModel() {

    private var _searchResultsStateFlow = MutableStateFlow(RecordsVo(emptyList()))
    val searchResultsStateFlow: StateFlow<RecordsVo> = _searchResultsStateFlow

    suspend fun get(query: String) {
        withMainContext {
            screenSwitchable.showProgressBar()
        }
        try {
            val records: RecordsVo = searchCarsByMakeUseCase.execute(query)
            _searchResultsStateFlow.value = records
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
                _searchResultsStateFlow.value = RecordsVo(emptyList())
                screenSwitchable.showError()
            }
        } finally {
            withContext(Dispatchers.Main) {
                screenSwitchable.hideProgressBar()
            }
        }
    }
}