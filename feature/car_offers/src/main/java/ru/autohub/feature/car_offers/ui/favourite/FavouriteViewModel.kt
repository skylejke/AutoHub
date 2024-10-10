package ru.autohub.feature.car_offers.ui.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.autohub.feature.car_offers.usecase.GetFavouritesUseCase
import ru.autohub.feature.common.model.CarVo
import ru.autohub.feature.common.usecase.GetCurrentUserUseCase

class FavouriteViewModel(
    private val getFavouritesUseCase: GetFavouritesUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

    private val _currentUser = MutableStateFlow<FirebaseUser?>(null)
    val currentUser: StateFlow<FirebaseUser?> get() = _currentUser

    private val _carsStateFlow = MutableStateFlow<List<CarVo>>(emptyList())
    val carsStateFlow: StateFlow<List<CarVo>> get() = _carsStateFlow

    init {
        viewModelScope.launch {
            _currentUser.value = getCurrentUserUseCase.execute()
            _currentUser.collectLatest { user ->
                if (user == null) {
                    _carsStateFlow.value = emptyList()
                    return@collectLatest
                }
                getFavouritesUseCase.execute()
                    .collectLatest { favouriteList ->
                        _carsStateFlow.value = favouriteList
                    }
            }
        }
    }

    fun refreshCurrentUser() {
        viewModelScope.launch {
            _currentUser.value = getCurrentUserUseCase.execute()
        }
    }
}
