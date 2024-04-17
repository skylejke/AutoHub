package com.example.autohub.model

import com.google.gson.annotations.SerializedName

data class Car(
    @SerializedName("bodyType")
    val bodyType: String,
    @SerializedName("condition")
    val condition: String,
    @SerializedName("displayColor")
    val displayColor: String,
    @SerializedName("make")
    val make: String,
    @SerializedName("mileage")
    val mileage: String,
    @SerializedName("model")
    val model: String,
    @SerializedName("photoUrls")
    val photoUrls: List<String>,
    @SerializedName("price")
    val price: String,
    @SerializedName("primaryPhotoUrl")
    val primaryPhotoUrl: String,
    @SerializedName("year")
    val year: Int
)