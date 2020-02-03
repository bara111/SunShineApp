package com.example.weatherapp.ui.details

import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.data.local.WeatherEntity
import javax.inject.Inject

class DetailsViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var weatherRepository: WeatherRepository
    suspend fun saveRecord(
        time: String,
        MaxTemp: String?,
        MinTemp: String?
    ) {
        val weatherEntity = WeatherEntity(time, MaxTemp, MinTemp)
        weatherRepository.weatherLocalDataSource.saveRecord(weatherEntity)
    }
}