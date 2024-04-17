package com.example.autohub

import com.example.autohub.model.Records
import retrofit2.http.GET
import retrofit2.http.Query

interface CarApi {

//    @GET("listings")
//    suspend fun searchCars(
//        @Query("query") query: String
//    ): Response<List<Record>>

    @GET("listings")
    suspend fun getCars(@Query("apikey") apiKey: String): Records
}