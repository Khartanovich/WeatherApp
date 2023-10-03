package com.example.weatherapp.ui.cities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class CitiesViewModelFactory @Inject constructor(
    private val viewModel: CitiesViewModel
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModel as T
    }
}