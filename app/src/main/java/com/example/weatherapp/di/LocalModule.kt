package com.example.weatherapp.di

import android.content.Context
import androidx.room.Room
import com.example.weatherapp.data.local.GetCitiesFromDataBaseRepositoryImpl
import com.example.weatherapp.data.local.GetPreviousCityNameRepositoryImpl
import com.example.weatherapp.data.local.WeatherInCityDao
import com.example.weatherapp.data.local.WeatherInCityDataBase
import com.example.weatherapp.domain.GetCitiesFromDataBaseRepository
import com.example.weatherapp.domain.GetPreviousCityNameRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalModule {

    @Singleton
    @Provides
    fun providesDataBase(context: Context): WeatherInCityDataBase {
        return Room.inMemoryDatabaseBuilder(context, WeatherInCityDataBase::class.java).build()
    }

    @Singleton
    @Provides
    fun providesDao(db: WeatherInCityDataBase): WeatherInCityDao {
        return db.weatherInCityDao()
    }

    @Singleton
    @Provides
    fun providesGetCitiesRepository(weatherInCityDao: WeatherInCityDao): GetCitiesFromDataBaseRepository {
        return GetCitiesFromDataBaseRepositoryImpl(weatherInCityDao)
    }

    @Singleton
    @Provides
    fun providesGetCityNameRepository(context: Context): GetPreviousCityNameRepository{
        return GetPreviousCityNameRepositoryImpl(context)
    }
}