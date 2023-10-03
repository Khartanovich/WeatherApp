package com.example.weatherapp.data

import android.util.Log
import com.example.weatherapp.data.local.WeatherInCityDao
import com.example.weatherapp.data.local.asCityModel
import com.example.weatherapp.data.local.asWeatherInCityModel
import com.example.weatherapp.data.network.CityModelNetwork
import com.example.weatherapp.data.network.WeatherApi
import com.example.weatherapp.data.network.asCityModel
import com.example.weatherapp.data.network.asWeatherInCityModel
import com.example.weatherapp.domain.CityModel
import com.example.weatherapp.domain.GetCityNameAndWeatherRepository
import com.example.weatherapp.domain.WeatherInCityModel
import com.example.weatherapp.domain.asWeatherInCityEntity
import javax.inject.Inject

class GetCityNameAndWeatherRepositoryImpl @Inject constructor(
    private val retrofitServices: WeatherApi,
    private val weatherInCityDao: WeatherInCityDao
) : GetCityNameAndWeatherRepository {
    override suspend fun getCityName(name: String): List<CityModel> {
        return kotlin.runCatching {
            retrofitServices.getNameCity(name)
                .map(CityModelNetwork::asCityModel)
        }.fold(
            onSuccess = { it },
            onFailure = {
                Log.d("MyLog", it.message ?: "")
                weatherInCityDao.getWeatherInCity(name).map {
                    it.asCityModel()
                }
            }
        )
    }

    override suspend fun getWeatherInCity(cityName: String): List<WeatherInCityModel> {
        return kotlin.runCatching {
            retrofitServices.getWeatherInCity(cityName).asWeatherInCityModel()
        }.fold(onSuccess = { listOf(it) },
            onFailure = {
                weatherInCityDao.getWeatherInCity(cityName).map { it.asWeatherInCityModel() }
            })
    }

    override fun saveWeatherInCity(weatherInCityModel: WeatherInCityModel) {
        weatherInCityDao.insertWeatherInCityEntity(weatherInCityModel.asWeatherInCityEntity())
    }
}