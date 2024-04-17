package com.example.autohub

import com.example.autohub.model.Records
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CarApi {

//    @GET("listings")
//    suspend fun searchCars(
//        @Query("query") query: String
//    ): Response<List<Record>>

    @GET("listings")
    suspend fun getCars(): Flow<Records>
}