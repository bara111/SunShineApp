package com.example.weatherapp.data.remote

import androidx.lifecycle.LiveData
import com.example.weatherapp.data.local.WeatherEntity
import com.example.weatherapp.data.models.WeatherResponse

interface WeatherDataSource {
    suspend fun saveRecord(weatherEntity: WeatherEntity)
    fun getRecords(): LiveData<List<WeatherEntity>>

    interface Local {
        suspend fun saveRecord(weatherEntity: WeatherEntity)
        fun getRecords(): LiveData<List<WeatherEntity>>
    }

    interface Remote
}