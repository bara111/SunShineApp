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
import com.example.weatherapp.data.remote.WeatherDataSource
import javax.inject.Inject

class MainViewModel @Inject constructor(var weatherRepository: WeatherRepository) : ViewModel(),
    WeatherDataSource.GetResponseCallback {
    var _weatherDailyDataList: MutableLiveData<ApiResponse<WeatherResponse, ErrorMessage>> =
        MutableLiveData()
    val weatherDailyDataList: LiveData<ApiResponse<WeatherResponse, ErrorMessage>> get() = _weatherDailyDataList
    var maxTemp: LiveData<Event<String>>
    var error: LiveData<Event<String>>

    init {
        weatherRepository.getTask(this)
        maxTemp = Transformations.map(_weatherDailyDataList) { response ->
            Event("${response?.Response?.list?.get(0)?.main?.converterTempMax()}")
        }

        error = Transformations.map(_weatherDailyDataList) { response ->
            Event("${response.error?.message}")
        }
    }

    override fun onSuccessCallback(ApiResponse: ApiResponse<WeatherResponse, ErrorMessage>) {
        _weatherDailyDataList.value = ApiResponse
    }

    override fun onFailureCallback(ApiResponse: ApiResponse<WeatherResponse, ErrorMessage>) {
        _weatherDailyDataList.value = ApiResponse
    }

    internal fun fetchNewDataOnRefresh() {
        weatherRepository.getTask(this)

    }
}




