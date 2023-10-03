package com.example.weatherapp.di

import android.content.Context
import com.example.weatherapp.ui.cities.CitiesFragment
import com.example.weatherapp.ui.search.SearchFragment
import com.example.weatherapp.ui.weather.WeatherDetailsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, LocalModule::class])
interface ApplicationComponent {

    fun inject(fragment: SearchFragment)
    fun inject(fragment: WeatherDetailsFragment)
    fun inject(fragment: CitiesFragment)

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}