package com.example.testmeteoexomind.meteo

import com.example.testmeteoexomind.network.MeteoResponse

data class MeteoCity (
    var cityName: String,
    var weather: MeteoResponse
)