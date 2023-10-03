package com.example.weatherapp.domain

interface GetCityNameAndWeatherRepository {
    suspend fun getCityName(name: String): List<CityModel>
    suspend fun getWeatherInCity(cityName: String): List<WeatherInCityModel>
    fun saveWeatherInCity(weatherInCityModel: WeatherInCityModel)
}