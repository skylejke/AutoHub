package com.example.autohub.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CarVo(
    val bodyType: String,
    val condition: String,
    val displayColor: String,
    val id: Int,
    val make: String,
    val mileage: String,
    val mileageUnformatted: Int,
    val model: String,
    val photoUrls: List<String>,
    val price: String,
    val priceUnformatted: Int,
    val primaryPhotoUrl: String,
    val vin: String,
    val year: Int
) : Parcelable  