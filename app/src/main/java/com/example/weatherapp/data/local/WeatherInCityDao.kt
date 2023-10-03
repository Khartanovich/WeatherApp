package com.example.weatherapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherInCityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeatherInCityEntity(weatherInCity: WeatherInCityEntity)

    @Query("SELECT * FROM weather_in_city WHERE cityName = :cityName")
    suspend fun getWeatherInCity(cityName: String): List<WeatherInCityEntity>

    @Delete
    suspend fun deleteWeatherInCity(weatherInCity: WeatherInCityEntity)

    @Query("SELECT * FROM weather_in_city")
    fun getCities(): Flow<List<WeatherInCityEntity>>
}