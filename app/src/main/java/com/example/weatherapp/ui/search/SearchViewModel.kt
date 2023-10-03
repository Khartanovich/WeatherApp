package com.example.weatherapp.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.CityModel
import com.example.weatherapp.domain.GetCityNameUseCase
import com.example.weatherapp.domain.GetWeatherInCityUseCase
import com.example.weatherapp.domain.SaveWeatherInCity
import com.example.weatherapp.domain.WeatherInCityModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val getNameCityNameUseCase: GetCityNameUseCase,
    private val getWeatherInCityUseCase: GetWeatherInCityUseCase,
    private val saveWeatherInCity: SaveWeatherInCity
) : ViewModel() {

    private val _cityName = MutableStateFlow<List<CityModel>>(emptyList())
    val cityName = _cityName.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun getNameCity(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                _isLoading.value = true
                getNameCityNameUseCase.getCityName(name)
            }.fold(
                onSuccess = { _cityName.value = it },
                onFailure = { Log.d("MyLog", it.message ?: "") }
            )
            _isLoading.value = false
        }
    }

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

    fun saveWeatherInCity(weatherInCityModel: WeatherInCityModel) {
        viewModelScope.launch(Dispatchers.IO) {
            saveWeatherInCity.saveWeatherInCity(weatherInCityModel)
        }
    }
}