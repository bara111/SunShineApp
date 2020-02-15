package com.example.weatherapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.weatherapp.Event
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.data.models.ApiResponse
import com.example.weatherapp.data.models.ErrorMessage
import com.example.weatherapp.data.models.WeatherResponse
import javax.inject.Inject

class MainViewModel @Inject constructor(weatherRepository: WeatherRepository) : ViewModel() {
    private var _weatherDailyDataList: MutableLiveData<ApiResponse<WeatherResponse, ErrorMessage>> =
        weatherRepository.getResponse()
    val weatherDailyDataList: LiveData<ApiResponse<WeatherResponse, ErrorMessage>> get() = _weatherDailyDataList
    var maxTemp: LiveData<Event<String>> = Transformations.map(weatherDailyDataList) { response ->
        Event("${response.Response?.list?.get(0)?.main?.converterTempMax()}")
    }
    var error: LiveData<Event<String>> = Transformations.map(weatherDailyDataList) { response ->
        Event("${response.error?.message}")
    }
}