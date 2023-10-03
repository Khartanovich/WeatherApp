package com.example.weatherapp.data.local

import com.example.weatherapp.domain.CityModel
import com.example.weatherapp.domain.GetCitiesFromDataBaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCitiesFromDataBaseRepositoryImpl @Inject constructor(
    private val weatherInCityDao: WeatherInCityDao
) : GetCitiesFromDataBaseRepository {
    override fun getCitiesFromDataBase(): Flow<List<CityModel>> {
        return weatherInCityDao.getCities().map {
            it.map { weatherInCityEntity -> weatherInCityEntity.asCityModel() }
        }
    }
}