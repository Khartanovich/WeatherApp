package com.example.weatherapp.domain

import javax.inject.Inject

class GetWeatherInCityUseCase @Inject constructor(private val repository: GetCityNameAndWeatherRepository) {
    suspend fun getWeatherInCity(cityName: String): List<WeatherInCityModel> {
        return repository.getWeatherInCity(cityName)
    }
}