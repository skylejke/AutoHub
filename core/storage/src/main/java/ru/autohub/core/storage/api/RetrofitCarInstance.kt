package ru.autohub.core.storage.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitCarInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://auto.dev/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: CarApi by lazy {
        retrofit.create(CarApi::class.java)
    }
}