package com.example.weatherapp.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class SearchViewModelFactory @Inject constructor(
    private val searchViewModel: SearchViewModel
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return searchViewModel as T
    }
}