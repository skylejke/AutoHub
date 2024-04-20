package com.example.autohub

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitCarInstance {

    //const val API_KEY = "ZrQEPSkKZGFuaWxtZWdhMjAwM0BnbWFpbC5jb20="

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