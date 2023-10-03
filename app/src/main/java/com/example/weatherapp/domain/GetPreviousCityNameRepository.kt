package com.example.weatherapp.domain

interface GetPreviousCityNameRepository {
    fun getPreviousCityName(): String?
    fun saveCityName(cityName: String)
}