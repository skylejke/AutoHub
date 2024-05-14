package com.example.autohub.ui.favourite

import androidx.lifecycle.LiveData
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

    val carsLiveData: LiveData<List<CarVo>> = getFavouritesUseCase.execute()

    fun getCurrentUser(): LiveData<FirebaseUser> {
        val user = MutableLiveData<FirebaseUser>()
        user.value = getCurrentUserUseCase.execute()
        return user
    }
}