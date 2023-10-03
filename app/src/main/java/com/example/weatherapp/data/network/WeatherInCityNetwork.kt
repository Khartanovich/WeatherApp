package com.example.weatherapp.data.network


import com.example.weatherapp.domain.WeatherInCityModel
import com.google.gson.annotations.SerializedName

data class WeatherInCityNetwork(
    @SerializedName("current")
    val current: Current,
    @SerializedName("location")
    val location: Location
)

fun WeatherInCityNetwork.asWeatherInCityModel() = WeatherInCityModel(
    cityName = location.name,
    time = location.localtime,
    tempC = current.tempC,
    country = location.country
)