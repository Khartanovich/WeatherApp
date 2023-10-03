package com.example.weatherapp.data.network

import com.example.weatherapp.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("search.json?key=${BuildConfig.API_KEY}")
    suspend fun getNameCity(@Query("q") q: String): List<CityModelNetwork>

    @GET("current.json?key=${BuildConfig.API_KEY}")
    suspend fun getWeatherInCity(@Query("q") q: String): WeatherInCityNetwork
}