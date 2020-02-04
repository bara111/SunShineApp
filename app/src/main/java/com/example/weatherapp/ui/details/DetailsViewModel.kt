package com.example.weatherapp.ui.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.data.local.WeatherEntity
import com.example.weatherapp.data.local.WeatherLocalDataSource
import com.example.weatherapp.data.remote.WeatherRemoteDataSource

class DetailsViewModel(application: Application) : AndroidViewModel(application) {
    private var weatherRepository: WeatherRepository
    var weatherLocalDataSource: WeatherLocalDataSource =
        WeatherLocalDataSource(application.applicationContext)
    var weatherRemoteDataSource: WeatherRemoteDataSource = WeatherRemoteDataSource()

    init {
        weatherRepository = WeatherRepository(weatherRemoteDataSource, weatherLocalDataSource)
    }

    suspend fun saveRecord(
        time: String,
        MaxTemp: String?,
        MinTemp: String?
    ) {
        val weatherEntity = WeatherEntity(time, MaxTemp, MinTemp)
        weatherRepository.weatherLocalDataSource.saveRecord(weatherEntity)
    }
}