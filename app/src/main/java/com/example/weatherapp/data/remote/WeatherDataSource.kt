package com.example.weatherapp.data.remote

import androidx.lifecycle.LiveData
import com.example.weatherapp.data.local.WeatherEntity
import com.example.weatherapp.data.models.WeatherResponse
import retrofit2.Response
import kotlin.Exception

interface WeatherDataSource {
    suspend fun saveRecord(weatherEntity: WeatherEntity)
    fun getRecords(): LiveData<List<WeatherEntity>>
    suspend fun getWeatherDetailsResponse(): Response<WeatherResponse>?
    fun getException():Exception?
    interface Local {
        suspend fun saveRecord(weatherEntity: WeatherEntity)
        fun getRecords(): LiveData<List<WeatherEntity>>
    }
    interface Remote
}