package ru.autohub.feature.car_offers.usecase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ru.autohub.core.storage.repository.CarDetailsRepository
import ru.autohub.feature.common.model.CarVo
import ru.autohub.feature.common.utils.mapToCarVoList

internal class GetFavouritesUseCase(private val carDetailsRepository: CarDetailsRepository) {
    fun execute(): StateFlow<List<CarVo>> {
        return carDetailsRepository.getFavourites().map {
            it.mapToCarVoList()
        }.stateIn(
            scope = CoroutineScope(Dispatchers.IO),
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    }
}