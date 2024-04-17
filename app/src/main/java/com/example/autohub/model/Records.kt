package com.example.autohub.model

import com.google.gson.annotations.SerializedName

data class Records(
    @SerializedName("records")
    val list: List<Car>,
)