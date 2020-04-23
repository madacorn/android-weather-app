package com.astain.forecastmvvm.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.astain.forecastmvvm.data.network.response.CurrentWeatherResponse
import com.astain.forecastmvvm.internal.NoConnectivityException

class WeatherNetworkDataSourceImpl(
    private val weatherApiService: WeatherApiService
) : WeatherNetworkDataSource {

    private val _downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()

    override val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadedCurrentWeather

    override suspend fun fetchCurrentWeather(
        location: String,
        languageCode: String,
        units: String
    ) {
        try {
            val fetchedCurrentWeather =
                weatherApiService.getCurrentWeather(location,languageCode,units)
                    .await()
            _downloadedCurrentWeather.postValue(fetchedCurrentWeather)
        } catch (e: NoConnectivityException)
        {
            Log.e("Connectivity", "No internet connection.", e)
        }
    }
}