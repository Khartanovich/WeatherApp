package com.example.weatherapp.ui.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class WeatherDetailsViewModelFactory @Inject constructor(
    private val weatherDetailsViewModel: WeatherDetailsViewModel
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return weatherDetailsViewModel as T
    }
}