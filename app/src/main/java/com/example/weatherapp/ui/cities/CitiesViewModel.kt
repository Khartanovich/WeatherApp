package com.example.weatherapp.ui.cities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.GetCitiesFromDataBaseUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class CitiesViewModel @Inject constructor(
    private val getCitiesFromDataBaseUseCase: GetCitiesFromDataBaseUseCase
) : ViewModel() {
    val cities = this.getCitiesFromDataBaseUseCase.getCitiesFromDataBase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )
}