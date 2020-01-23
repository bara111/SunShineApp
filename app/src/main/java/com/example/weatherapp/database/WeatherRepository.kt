package com.example.weatherapp.database

import androidx.lifecycle.LiveData

class WeatherRepository(private val weatherDao: WeatherDao) {

    val weatherRecords: LiveData<List<WeatherEntity>> = weatherDao.getRecords()

    suspend fun insert(weatherEntity: WeatherEntity) {
        weatherDao.insert(weatherEntity)
    }
}