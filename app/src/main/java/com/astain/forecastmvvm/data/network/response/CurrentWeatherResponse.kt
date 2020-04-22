package com.astain.forecastmvvm.data.network.response

import com.astain.forecastmvvm.data.db.entity.CurrentWeatherEntry
import com.astain.forecastmvvm.data.db.entity.Location
import com.google.gson.annotations.SerializedName


data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,
    val location: Location
)