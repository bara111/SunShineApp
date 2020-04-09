package com.example.weatherapp.data.remote

import androidx.lifecycle.LiveData
import com.example.weatherapp.data.local.WeatherEntity

interface WeatherDataSource {
    suspend fun addDailyRecordToDataBase(weatherEntity: WeatherEntity)
    fun getDailyRecordsFromDataBase(): LiveData<List<WeatherEntity>>

    interface Local {
        suspend fun addDailyRecordToDataBase(weatherEntity: WeatherEntity)
        fun getDailyRecordsFromDataBase(): LiveData<List<WeatherEntity>>
    }

    interface Remote
}