package com.example.weatherapp.ui.weather

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.GetWeatherInCityUseCase
import com.example.weatherapp.domain.WeatherInCityModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherDetailsViewModel @Inject constructor(
    private val getWeatherInCityUseCase: GetWeatherInCityUseCase
) : ViewModel() {

    private val _weatherInCity = MutableStateFlow<List<WeatherInCityModel?>>(emptyList())
    val weatherInCity = _weatherInCity.asStateFlow()

    fun getWeatherInCity(cityName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                getWeatherInCityUseCase.getWeatherInCity(cityName)
            }.fold(
                onSuccess = { _weatherInCity.value = it },
                onFailure = { Log.d("MyLog", it.message ?: "") }
            )
        }
    }
}