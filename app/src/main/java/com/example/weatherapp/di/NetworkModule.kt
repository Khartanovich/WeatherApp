package com.example.weatherapp.di

import com.example.weatherapp.data.local.WeatherInCityDao
import com.example.weatherapp.data.GetCityNameAndWeatherRepositoryImpl
import com.example.weatherapp.data.network.WeatherApi
import com.example.weatherapp.domain.GetCityNameAndWeatherRepository
import com.example.weatherapp.utils.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesWeatherApi(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

    @Singleton
    @Provides
    fun providesGetNameCityRepo(
        weatherApi: WeatherApi,
        weatherInCityDao: WeatherInCityDao
    ): GetCityNameAndWeatherRepository {
        return GetCityNameAndWeatherRepositoryImpl(weatherApi, weatherInCityDao)
    }
}