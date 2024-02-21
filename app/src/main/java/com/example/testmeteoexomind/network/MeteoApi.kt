package com.example.testmeteoexomind.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface MeteoApi  {

    @Headers("Content-Type: application/json")
    @GET("weather")
    suspend fun obtainWeatherForCity(@Query("q") city: String, @Query("APPID") apiKey: String, @Query("units") units: String): Response<MeteoResponse>

}