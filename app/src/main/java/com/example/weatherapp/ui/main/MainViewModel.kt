package com.example.weatherapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.data.models.WeatherDailyData
import javax.inject.Inject

class MainViewModel @Inject constructor(weatherRepository: WeatherRepository) : ViewModel() {
    var weatherDailyDataList: MutableLiveData<List<WeatherDailyData>> =
        weatherRepository.getResponse()
}