package com.example.weatherapp.data

import com.example.weatherapp.data.local.WeatherEntity
import com.example.weatherapp.data.local.WeatherLocalDataSource
import com.example.weatherapp.data.models.WeatherResponse
import com.example.weatherapp.data.remote.WeatherRemoteDataSource
import javax.inject.Inject

class WeatherRepository @Inject constructor() : WeatherDataSource {
    @Inject
    lateinit var weatherRemoteDataSource: WeatherRemoteDataSource

    var weatherLocalDataSource: WeatherLocalDataSource = WeatherLocalDataSource()
    override suspend fun saveRecord(weatherEntity: WeatherEntity) {
        weatherLocalDataSource.saveRecord(weatherEntity)
    }

    override suspend fun getRecords(): List<WeatherEntity> {
        return weatherLocalDataSource.getRecords()
    }

    override fun getResponse(): WeatherResponse? {
        weatherRemoteDataSource.requestData()
        return weatherRemoteDataSource.getResponse()
    }

}