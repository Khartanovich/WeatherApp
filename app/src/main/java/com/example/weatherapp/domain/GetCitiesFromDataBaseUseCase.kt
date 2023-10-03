package com.example.weatherapp.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCitiesFromDataBaseUseCase @Inject constructor(private val repository: GetCitiesFromDataBaseRepository) {
    fun getCitiesFromDataBase(): Flow<List<CityModel>> {
        return repository.getCitiesFromDataBase()
    }
}