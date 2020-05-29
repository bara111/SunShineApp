package com.example.weatherapp.data

import androidx.lifecycle.LiveData
import com.example.weatherapp.data.local.WeatherEntity
import com.example.weatherapp.data.local.WeatherLocalDataSource
import com.example.weatherapp.data.models.WeatherResponse
import com.example.weatherapp.data.remote.WeatherDataSource
import com.example.weatherapp.data.remote.WeatherRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private var weatherRemoteDataSource: WeatherRemoteDataSource,
    var weatherLocalDataSource: WeatherLocalDataSource
) : WeatherDataSource {
    override suspend fun saveRecord(weatherEntity: WeatherEntity) {
        weatherLocalDataSource.saveRecord(weatherEntity)
    }
    override fun getRecords(): LiveData<List<WeatherEntity>> {
        return weatherLocalDataSource.getRecords()
    }
    override suspend fun getWeatherDetailsResponse(): Response<WeatherResponse>? {
       return weatherRemoteDataSource.requestData()
    }

    override fun getException(): Exception? {
        return weatherRemoteDataSource.exception
    }
}