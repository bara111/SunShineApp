package com.example.weatherapp.data.remote

import androidx.lifecycle.LiveData
import com.example.weatherapp.data.local.WeatherEntity
import com.example.weatherapp.data.models.ApiResponse
import com.example.weatherapp.data.models.ErrorMessage
import com.example.weatherapp.data.models.WeatherResponse

interface WeatherDataSource {
    suspend fun saveRecord(weatherEntity: WeatherEntity)
    fun getRecords(): LiveData<List<WeatherEntity>>
    fun getTask(callback: GetResponseCallback)
    interface Local {
        suspend fun saveRecord(weatherEntity: WeatherEntity)
        fun getRecords(): LiveData<List<WeatherEntity>>
    }

    interface Remote
    interface GetResponseCallback {
        fun onSuccessCallback(ApiResponse: ApiResponse<WeatherResponse, ErrorMessage>)
    }
}