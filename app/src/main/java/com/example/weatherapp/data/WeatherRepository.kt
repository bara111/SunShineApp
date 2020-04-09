package com.example.weatherapp.data

import androidx.lifecycle.LiveData
import com.example.weatherapp.data.local.WeatherEntity
import com.example.weatherapp.data.local.WeatherLocalDataSource
import com.example.weatherapp.data.remote.WeatherDataSource
import com.example.weatherapp.data.remote.WeatherRemoteDataSource
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    var weatherRemoteDataSource: WeatherRemoteDataSource,
    var weatherLocalDataSource: WeatherLocalDataSource

) : WeatherDataSource {
    override suspend fun addDailyRecordToDataBase(weatherEntity: WeatherEntity) {
        weatherLocalDataSource.addDailyRecordToDataBase(weatherEntity)
    }

    override fun getDailyRecordsFromDataBase(): LiveData<List<WeatherEntity>> {
        return weatherLocalDataSource.getDailyRecordsFromDataBase()
    }
}