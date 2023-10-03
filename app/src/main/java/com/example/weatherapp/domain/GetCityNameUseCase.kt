package com.example.weatherapp.domain

import javax.inject.Inject

class GetCityNameUseCase @Inject constructor(private val repository: GetCityNameAndWeatherRepository) {
    suspend fun getCityName(name: String): List<CityModel> {
        return repository.getCityName(name)
    }
}