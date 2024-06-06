
package com.example.autohub.ui.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.autohub.domain.model.CarVo
import com.example.autohub.domain.usecase.GetCurrentUserUseCase
import com.example.autohub.domain.usecase.GetFavouritesUseCase
import com.google.firebase.auth.FirebaseUser

class FavouriteViewModel(
    private val getFavouritesUseCase: GetFavouritesUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

    private val _currentUser = MutableLiveData<FirebaseUser?>()
    val currentUser: LiveData<FirebaseUser?> get() = _currentUser

    private val _carsLiveData = MediatorLiveData<List<CarVo>>()
    val carsLiveData: LiveData<List<CarVo>> get() = _carsLiveData

    init {
        _carsLiveData.addSource(_currentUser) { user ->
            user?.let {
                _carsLiveData.addSource(getFavouritesUseCase.execute()) { favouriteList ->
                    _carsLiveData.value = favouriteList
                }
            } ?: run {
                _carsLiveData.value = emptyList()
            }
        }
        _currentUser.value = getCurrentUserUseCase.execute()
    }

    fun refreshCurrentUser() {
        _currentUser.value = getCurrentUserUseCase.execute()
    }
}