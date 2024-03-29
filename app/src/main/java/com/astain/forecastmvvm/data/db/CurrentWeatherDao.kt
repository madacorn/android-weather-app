package com.astain.forecastmvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.astain.forecastmvvm.data.db.entity.CURRENT_WEATHER_ID
import com.astain.forecastmvvm.data.db.entity.CurrentWeatherEntry

@Dao
interface CurrentWeatherDao {

    //Insert or update
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry: CurrentWeatherEntry)

    @Query("select * from current_weather where id = $CURRENT_WEATHER_ID")
    fun getWeather(): LiveData<CurrentWeatherEntry>

}