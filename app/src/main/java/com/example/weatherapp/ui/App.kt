package com.example.weatherapp.ui

import android.app.Application
import com.example.weatherapp.di.ApplicationComponent
import com.example.weatherapp.di.DaggerApplicationComponent

class App : Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}