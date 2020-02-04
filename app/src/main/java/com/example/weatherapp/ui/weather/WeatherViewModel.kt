package com.example.weatherapp.ui.weather

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.data.local.WeatherEntity
import com.example.weatherapp.data.local.WeatherLocalDataSource
import com.example.weatherapp.data.remote.WeatherRemoteDataSource

class WeatherViewModel(application: Application) : AndroidViewModel(application) {
    var weatherRepository: WeatherRepository
    var weatherDataBaseList: LiveData<List<WeatherEntity>>
    var weatherLocalDataSource: WeatherLocalDataSource =
        WeatherLocalDataSource(application.applicationContext)
    var weatherRemoteDataSource: WeatherRemoteDataSource = WeatherRemoteDataSource()

    init {
        weatherRepository = WeatherRepository(weatherRemoteDataSource, weatherLocalDataSource)
        weatherDataBaseList = weatherRepository.getRecords()
    }
}