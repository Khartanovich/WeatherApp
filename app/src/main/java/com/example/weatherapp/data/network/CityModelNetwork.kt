package com.example.weatherapp.data.network

import com.example.weatherapp.domain.CityModel

data class CityModelNetwork(
    val country: String,
    val id: Int,
    val lat: Double,
    val lon: Double,
    val name: String,
    val region: String,
    val url: String
)

fun CityModelNetwork.asCityModel() = CityModel(
    country = country,
    name = name,
)

