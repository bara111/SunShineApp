package com.example.weatherapp.data

import androidx.lifecycle.LiveData
import com.example.weatherapp.data.local.WeatherEntity
import com.example.weatherapp.data.local.WeatherLocalDataSource
import com.example.weatherapp.data.models.WeatherResponse
import com.example.weatherapp.data.remote.WeatherDataSource
import com.example.weatherapp.data.remote.WeatherService
import retrofit2.Retrofit
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    var weatherLocalDataSource: WeatherLocalDataSource, retrofit: Retrofit
) : WeatherDataSource {
    var weatherResponse:WeatherResponse?=null
    private val service: WeatherService = retrofit.create(WeatherService::class.java)

    suspend fun requestData(
        Lat: String,
        Lon: String,
        ApiKey: String
    ): WeatherResponse? {
        weatherResponse=service.getCurrentWeatherData(Lat, Lon, ApiKey)
        return weatherResponse
    }

    override suspend fun saveRecord(weatherEntity: WeatherEntity) {
        weatherLocalDataSource.saveRecord(weatherEntity)
    }

    override fun getRecords(): LiveData<List<WeatherEntity>> {
        return weatherLocalDataSource.getRecords()
    }


}