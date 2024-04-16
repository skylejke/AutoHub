package com.example.autohub

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CarApi {
    @GET("listings")
    suspend fun searchCars(
        @Query("query") query: String
    ): Response<List<Car>>
}