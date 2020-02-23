package com.example.weatherapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.data.local.WeatherEntity
import com.example.weatherapp.data.local.WeatherLocalDataSource
import com.example.weatherapp.data.models.ApiResponse
import com.example.weatherapp.data.models.ErrorMessage
import com.example.weatherapp.data.models.WeatherResponse
import com.example.weatherapp.data.remote.WeatherDataSource
import com.example.weatherapp.data.remote.WeatherRemoteDataSource
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private var weatherRemoteDataSource: WeatherRemoteDataSource,
    var weatherLocalDataSource: WeatherLocalDataSource

) : WeatherDataSource {
    lateinit var weatherResponse: MutableLiveData<ApiResponse<WeatherResponse, ErrorMessage>>
    override suspend fun saveRecord(weatherEntity: WeatherEntity) {
        weatherLocalDataSource.saveRecord(weatherEntity)
    }

    override fun getRecords(): LiveData<List<WeatherEntity>> {
        return weatherLocalDataSource.getRecords()
    }

    override fun fetchDatafromApi():MutableLiveData<ApiResponse<WeatherResponse, ErrorMessage>> {
        return weatherRemoteDataSource.requestData()
    }
}