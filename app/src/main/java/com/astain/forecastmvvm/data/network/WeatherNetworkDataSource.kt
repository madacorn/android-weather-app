package com.astain.forecastmvvm.data.network

import androidx.lifecycle.LiveData
import com.astain.forecastmvvm.data.network.response.CurrentWeatherResponse

interface WeatherNetworkDataSource {
    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>

    suspend fun  fetchCurrentWeather(
        location: String,
        languageCode: String,
        units: String = "m"
    )
}