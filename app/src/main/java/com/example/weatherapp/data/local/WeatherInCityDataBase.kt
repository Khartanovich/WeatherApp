package com.example.weatherapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WeatherInCityEntity::class], version = 1)
abstract class WeatherInCityDataBase: RoomDatabase() {
    abstract fun weatherInCityDao(): WeatherInCityDao
}