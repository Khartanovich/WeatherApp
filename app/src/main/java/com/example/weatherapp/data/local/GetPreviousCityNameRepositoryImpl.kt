package com.example.weatherapp.data.local

import android.content.Context
import android.content.SharedPreferences
import com.example.weatherapp.domain.GetPreviousCityNameRepository
import com.example.weatherapp.utils.Constants
import javax.inject.Inject

class GetPreviousCityNameRepositoryImpl @Inject constructor(context: Context) : GetPreviousCityNameRepository {
    private var preference: SharedPreferences = context.getSharedPreferences(Constants.SHARED_PREF_CITY_NAME, Context.MODE_PRIVATE)
    private var editor = preference.edit()

    override fun getPreviousCityName(): String? {
        return preference.getString(Constants.PREF_KEY_CITY_NAME, "Moscow")
    }

    override fun saveCityName(cityName: String) {
        editor.putString(Constants.PREF_KEY_CITY_NAME, cityName)
        editor.apply()
    }
}