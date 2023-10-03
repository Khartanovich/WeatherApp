package com.example.weatherapp.domain

import kotlinx.coroutines.flow.Flow

interface GetCitiesFromDataBaseRepository {
    fun getCitiesFromDataBase(): Flow<List<CityModel>>
}