package com.example.weatherapp.data.local

import androidx.room.Entity
import com.example.weatherapp.domain.CityModel
import com.example.weatherapp.domain.WeatherInCityModel

@Entity(tableName = "weather_in_city", primaryKeys = ["cityName", "time", "country"])
data class WeatherInCityEntity(
    val cityName: String,
    val time: String,
    val tempC: Double,
    val country: String
)

fun WeatherInCityEntity.asWeatherInCityModel() = WeatherInCityModel(
    cityName = cityName, time = time, tempC = tempC, country = country
)

fun WeatherInCityEntity.asCityModel() = CityModel(country = country, name = cityName)