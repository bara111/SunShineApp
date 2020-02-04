package com.example.weatherapp.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.data.local.WeatherLocalDataSource
import com.example.weatherapp.data.models.WeatherDailyData
import com.example.weatherapp.data.remote.WeatherRemoteDataSource

class MainViewModel(application: Application) : AndroidViewModel(application) {
    var weatherDailyDataList: MutableLiveData<List<WeatherDailyData>>
    var weatherLocalDataSource: WeatherLocalDataSource =
        WeatherLocalDataSource(application.applicationContext)
    var weatherRemoteDataSource: WeatherRemoteDataSource = WeatherRemoteDataSource()

    private var weatherRepository: WeatherRepository

    init {
        weatherRepository = WeatherRepository(weatherRemoteDataSource, weatherLocalDataSource)
        weatherDailyDataList = weatherRepository.getResponse()
    }
}