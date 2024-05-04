package com.example.autohub.domain.model

data class CarDomain(
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
)