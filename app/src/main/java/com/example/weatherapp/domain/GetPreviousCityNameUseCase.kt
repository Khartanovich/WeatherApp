package com.example.weatherapp.domain

import javax.inject.Inject

class GetPreviousCityNameUseCase @Inject constructor(private val repository: GetPreviousCityNameRepository) {
    fun getPreviousCityName(): String? {
        return repository.getPreviousCityName()
    }
}