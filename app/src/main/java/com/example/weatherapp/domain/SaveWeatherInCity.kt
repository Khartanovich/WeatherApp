package com.example.weatherapp.domain

import javax.inject.Inject

class SaveWeatherInCity @Inject constructor(private val repository: GetCityNameAndWeatherRepository) {
    fun saveWeatherInCity(weatherInCityModel: WeatherInCityModel) {
        repository.saveWeatherInCity(weatherInCityModel)
    }
}