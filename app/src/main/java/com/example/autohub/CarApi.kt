package com.example.autohub

import com.example.autohub.model.Records
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CarApi {

    @GET("listings")
    suspend fun searchCarsByMake(
        @Query("make") make: String
    ): Response<Records>

    @GET("listings")
    suspend fun getCars(): Records
}