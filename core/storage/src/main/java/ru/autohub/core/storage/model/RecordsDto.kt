package ru.autohub.core.storage.model

import com.google.gson.annotations.SerializedName

data class RecordsDto(
    @SerializedName("records")
    val list: List<CarDto>,
)