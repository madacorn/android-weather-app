package com.astain.forecastmvvm.data.repository

import androidx.lifecycle.LiveData
import com.astain.forecastmvvm.data.db.CurrentWeatherDao
import com.astain.forecastmvvm.data.db.entity.CurrentWeatherEntry
import com.astain.forecastmvvm.data.network.WeatherNetworkDataSource
import com.astain.forecastmvvm.data.network.response.CurrentWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime
import java.util.*

class ForecastRepositoryImpl (
    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource
) : ForecastRepository {

    init {
        weatherNetworkDataSource.downloadedCurrentWeather.observeForever { newCurrentWeather ->
            //persist
            persistFetchedCurrentWeather(newCurrentWeather)
        }
    }
    override suspend fun getCurrentWeather(): LiveData<CurrentWeatherEntry> {
        return withContext(Dispatchers.IO) {
            return@withContext currentWeatherDao.getWeather()
        }
    }

    private fun persistFetchedCurrentWeather(fetchedWeather: CurrentWeatherResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(fetchedWeather.currentWeatherEntry)
        }
    }
    private suspend fun initWeatherData() {
        if(isFetchCurrentNeeded( ZonedDateTime.now().minusHours(1))) {
            fetchCurrentWeather()
        }
    }
    private suspend fun fetchCurrentWeather() {
        weatherNetworkDataSource.fetchCurrentWeather("London", Locale.getDefault().language)
    }

    private fun isFetchCurrentNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }
}