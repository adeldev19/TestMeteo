package com.example.testmeteoexomind.meteo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MeteoViewModel @Inject constructor(
    private val meteoRepository: MeteoRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    private val MessagesDattente = listOf(
        "Nous telechargeons les données",
        "C'est presque fini",
        "Plus que quelques secondes avant d’avoir le résultat"
    )

    private val cities = listOf(
        "Rennes",
        "Paris",
        "Nantes",
        "Bordeaux",
        "Lyon",
        "Grenoble"
    )

    fun initiateDataFetching() {
        viewModelScope.launch {
            loadDataProgressInOneMinute()
        }
        viewModelScope.launch {
            loadingCitiesWeather()
        }
        viewModelScope.launch {
            while(true) {
                loadingWaitingText()
            }
        }
    }

    private suspend fun loadDataProgressInOneMinute() {
        for (percentage in 0..100) {
            delay(600)
            _uiState.update { currentState ->
                currentState.copy(loadingCurrentProgress = percentage.toFloat())
            }
        }
        delay(2000)
        _uiState.update { currentState ->
            currentState.copy(isDone = true)
        }
    }

    private suspend fun loadingWaitingText() {
        for (text in MessagesDattente) {
            _uiState.update { currentState ->
                currentState.copy(messageDattente = text)
            }
            delay(6000)
        }
    }

    private suspend fun loadingCitiesWeather() {
        val listOfMeteoCity=  mutableListOf<MeteoCity>()
        for (city in cities) {
            val meteoResponse = meteoRepository.findWeatherByCity(city)
            meteoResponse?.let {
                listOfMeteoCity.add(MeteoCity(city, it))
                _uiState.update { currentState ->
                    currentState.copy(listOfMeteoCity = listOfMeteoCity)
                }
            }
            delay(10000)
        }
    }

}

data class WeatherUiState(
    val loadingCurrentProgress: Float = 0f,
    val messageDattente: String = "",
    val listOfMeteoCity: List<MeteoCity> = emptyList(),
    val isDone: Boolean = false
)