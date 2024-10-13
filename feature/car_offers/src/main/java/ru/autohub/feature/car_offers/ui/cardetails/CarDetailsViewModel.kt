package ru.autohub.feature.car_offers.ui.cardetails

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.autohub.feature.car_offers.usecase.AddToFavouriteUseCase
import ru.autohub.feature.car_offers.usecase.CheckIfCarIsFavoutriteUseCase
import ru.autohub.feature.car_offers.usecase.DeleteFromFavouriteUseCase
import ru.autohub.feature.common.model.CarVo
import ru.autohub.feature.common.usecase.GetCurrentUserUseCase

internal class CarDetailsViewModel(
    private val checkIfCarIsFavoutriteUseCase: CheckIfCarIsFavoutriteUseCase,
    private val addToFavouriteUseCase: AddToFavouriteUseCase,
    private val deleteFromFavouriteUseCase: DeleteFromFavouriteUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

    fun checkIfCarIsFavoutrite(carVo: CarVo): StateFlow<Boolean> {
        return checkIfCarIsFavoutriteUseCase.execute(carVo)
    }

    fun addToFavourite(id: String, carMap: HashMap<String, Any>) {
        addToFavouriteUseCase.execute(id, carMap)
    }

    fun deleteFromFavourite(id: String) {
        deleteFromFavouriteUseCase.execute(id)
    }

    fun getCurrentUser(): StateFlow<FirebaseUser?> {
        val user = MutableStateFlow<FirebaseUser?>(null)
        user.value = getCurrentUserUseCase.execute()
        return user
    }

}