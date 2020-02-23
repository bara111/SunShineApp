package com.example.weatherapp.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.data.local.WeatherEntity
import com.example.weatherapp.data.models.ApiResponse
import com.example.weatherapp.data.models.ErrorMessage
import com.example.weatherapp.data.models.WeatherResponse

interface WeatherDataSource {
    suspend fun saveRecord(weatherEntity: WeatherEntity)
    fun getRecords(): LiveData<List<WeatherEntity>>
    fun fetchDatafromApi(): MutableLiveData<ApiResponse<WeatherResponse, ErrorMessage>>
    interface Local {
        suspend fun saveRecord(weatherEntity: WeatherEntity)
        fun getRecords(): LiveData<List<WeatherEntity>>
    }

    interface Remote
}