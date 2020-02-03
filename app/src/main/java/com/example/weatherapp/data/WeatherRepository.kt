package com.example.weatherapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.data.local.WeatherEntity
import com.example.weatherapp.data.local.WeatherLocalDataSource
import com.example.weatherapp.data.models.WeatherDailyData
import com.example.weatherapp.data.remote.WeatherDataSource
import com.example.weatherapp.data.remote.WeatherRemoteDataSource
import javax.inject.Inject

class WeatherRepository @Inject constructor() :
    WeatherDataSource.Remote, WeatherDataSource.Local {
    @Inject
    lateinit var weatherRemoteDataSource: WeatherRemoteDataSource
    @Inject
    lateinit var weatherLocalDataSource: WeatherLocalDataSource

    override suspend fun saveRecord(weatherEntity: WeatherEntity) {
        weatherLocalDataSource.saveRecord(weatherEntity)
    }

    override fun getRecords(): LiveData<List<WeatherEntity>> {
        return weatherLocalDataSource.getRecords()
    }

    override fun getResponse(): MutableLiveData<List<WeatherDailyData>> {
        weatherRemoteDataSource.requestData()
        return weatherRemoteDataSource.getResponse()
    }
}