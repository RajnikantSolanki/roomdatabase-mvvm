package com.example.vcerp_practical.API

import com.example.example.CityResponse
import com.example.example.DistResponse
import com.example.example.StateResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface Apis {

    @GET("getcity")
    fun getCity(): Deferred<CityResponse>

    @GET("/getstate")
    fun getState(): Deferred<StateResponse>

    @GET("getDist")
    fun getDist(): Deferred<DistResponse>

}