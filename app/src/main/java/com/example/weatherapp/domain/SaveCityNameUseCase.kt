package com.example.weatherapp.domain

import javax.inject.Inject

class SaveCityNameUseCase @Inject constructor(private val repository: GetPreviousCityNameRepository) {
    fun saveCityName(cityName: String) {
        repository.saveCityName(cityName)
    }
}