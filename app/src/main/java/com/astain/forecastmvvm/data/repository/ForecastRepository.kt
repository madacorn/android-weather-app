package com.astain.forecastmvvm.data.repository

import androidx.lifecycle.LiveData
import com.astain.forecastmvvm.data.db.entity.CurrentWeatherEntry

interface ForecastRepository {

    suspend fun getCurrentWeather(): LiveData<CurrentWeatherEntry>

}