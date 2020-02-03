package com.example.weatherapp.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.data.models.WeatherResponse
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var weatherRepository: WeatherRepository
     val weatherDailyDataList: MutableLiveData<WeatherResponse>? by lazy {
        MutableLiveData<WeatherResponse>()
    }

    fun getDataFromWeatherRepository() {
        weatherDailyDataList?.value = weatherRepository.getResponse()
    }


}