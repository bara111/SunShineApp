package com.example.weatherapp.data

import com.example.weatherapp.data.local.WeatherEntity
import com.example.weatherapp.data.models.WeatherResponse

interface WeatherDataSource {
    suspend fun saveRecord(weatherEntity: WeatherEntity)
    suspend fun getRecords(): List<WeatherEntity>
    fun getResponse(): WeatherResponse?
}