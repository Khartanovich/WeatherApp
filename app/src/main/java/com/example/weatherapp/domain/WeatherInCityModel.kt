package com.example.weatherapp.domain

import com.example.weatherapp.data.local.WeatherInCityEntity

data class WeatherInCityModel(
    val cityName: String,
    val time: String,
    val tempC: Double,
    val country: String
)

fun WeatherInCityModel.asWeatherInCityEntity() = WeatherInCityEntity(
    cityName = cityName,
    time = time,
    tempC = tempC,
    country = country
)
