package com.example.weatherapp.data.remote

import androidx.lifecycle.LiveData
import com.example.weatherapp.data.local.WeatherEntity

interface WeatherDataSource {
    suspend fun saveRecord(weatherEntity: WeatherEntity)
    fun getRecords(): LiveData<List<WeatherEntity>>

    interface Local {
        suspend fun saveRecord(weatherEntity: WeatherEntity)
        fun getRecords(): LiveData<List<WeatherEntity>>
    }

    interface Remote
}