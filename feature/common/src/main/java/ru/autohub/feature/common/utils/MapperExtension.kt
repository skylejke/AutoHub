package ru.autohub.feature.common.utils

import ru.autohub.core.storage.model.CarDto
import ru.autohub.core.storage.model.RecordsDto
import ru.autohub.feature.common.model.CarVo
import ru.autohub.feature.common.model.RecordsVo

fun List<CarDto>.mapToCarVoList(): List<CarVo> = map { car ->
    CarVo(
        bodyType = car.bodyType,
        condition = car.condition,
        displayColor = car.displayColor ?: "",
        id = car.id,
        make = car.make,
        mileage = car.mileage,
        model = car.model,
        photoUrls = car.photoUrls,
        price = car.price,
        primaryPhotoUrl = car.primaryPhotoUrl,
        vin = car.vin,
        year = car.year
    )
}

fun RecordsDto.mapToRecordsVo(): RecordsVo = RecordsVo(list = list.mapToCarVoList())

fun CarVo.mapToCarVo(): CarDto =
    CarDto(
        bodyType = bodyType,
        condition = condition,
        displayColor = displayColor,
        id = id,
        make = make,
        mileage = mileage,
        model = model,
        photoUrls = photoUrls,
        price = price,
        primaryPhotoUrl = primaryPhotoUrl,
        vin = vin,
        year = year
    )