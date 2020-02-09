package com.example.weatherapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.data.models.WeatherDailyData
import javax.inject.Inject

class MainViewModel @Inject constructor(weatherRepository: WeatherRepository) : ViewModel() {
    private var _weatherDailyDataList: MutableLiveData<List<WeatherDailyData>> = weatherRepository.getResponse()
          var weatherDailyDataList:LiveData<List<WeatherDailyData>>? get() = _weatherDailyDataList
    init {
        weatherDailyDataList=null
    }
}