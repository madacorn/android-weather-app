package com.astain.forecastmvvm.ui.weather.current

import androidx.lifecycle.ViewModel
import com.astain.forecastmvvm.data.repository.ForecastRepository
import com.astain.forecastmvvm.internal.lazyDeferred

class CurrentWeatherViewModel (
    private val forecastRepository: ForecastRepository
) : ViewModel() {

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather()
    }

}
