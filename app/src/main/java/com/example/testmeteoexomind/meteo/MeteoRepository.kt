package com.example.testmeteoexomind.meteo



import com.example.testmeteoexomind.network.MeteoApi
import com.example.testmeteoexomind.network.MeteoResponse
import javax.inject.Inject

interface MeteoRepository  {
    suspend fun findWeatherByCity(city: String): MeteoResponse?
}

class MeteoRepositoryImpl @Inject constructor(
    private val weatherApi: MeteoApi
) : MeteoRepository {


    private val apiKey =  "1682beb8faf29e731a6dfcd09c0a1ae2"

    override suspend fun findWeatherByCity(city: String): MeteoResponse? {
        val apiCall = weatherApi.obtainWeatherForCity(city, apiKey, "metric")
        return if(apiCall.isSuccessful) {
            apiCall.body()
        } else {
            null
        }
    }



}