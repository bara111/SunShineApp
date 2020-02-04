package com.example.weatherapp.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.data.local.WeatherEntity
import com.example.weatherapp.data.models.WeatherDailyData

interface WeatherDataSource {
    suspend fun saveRecord(weatherEntity: WeatherEntity)
     fun getRecords(): LiveData<List<WeatherEntity>>
    fun getResponse(): MutableLiveData<List<WeatherDailyData>>

    interface Local {
        suspend fun saveRecord(weatherEntity: WeatherEntity)
        fun getRecords(): LiveData<List<WeatherEntity>>
    }

    interface Remote {
        fun getResponse(): MutableLiveData<List<WeatherDailyData>>
    }
}