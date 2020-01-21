package com.example.weatherapp.database

import androidx.lifecycle.LiveData

class WeatherRepository(private val weatherDao: WeatherDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWeatherRecord: LiveData<List<WeatherEntity>> = weatherDao.getRecords()

    suspend fun insert(weatherEntity: WeatherEntity) {
        weatherDao.insert(weatherEntity)
    }
}