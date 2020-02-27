package com.example.weatherapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.weatherapp.Event
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.data.models.WeatherResponse
import com.example.weatherapp.data.remote.WeatherDataSource
import javax.inject.Inject

class MainViewModel @Inject constructor(private var weatherRepository: WeatherRepository) :
    ViewModel(),
    WeatherDataSource.GetResponseCallback {
    private var _weatherDailyDataList: MutableLiveData<Result<WeatherResponse>> =
        MutableLiveData()
    private var _errorResponse: MutableLiveData<Result<Nothing>> =
        MutableLiveData()
    val weatherDailyDataList: LiveData<Result<WeatherResponse>> get() = _weatherDailyDataList
    var maxTemp: LiveData<Event<String>>
    var error: LiveData<Event<String>>

    init {
        weatherRepository.getTask(this)
        maxTemp = Transformations.map(_weatherDailyDataList) { response ->
            Event("${response.getOrNull()?.list?.get(0)?.main?.converterTempMax()
            }")
        }

        error = Transformations.map(_errorResponse) { response ->
            Event("${response.onFailure { it }}}")
        }
    }

    override fun onSuccessCallback(ApiResponse: Result<WeatherResponse>) {
        _weatherDailyDataList.value=ApiResponse.map { it }
    }

    override fun onFailCallback(ApiResponse: Result<Nothing>) {
        _errorResponse.value=ApiResponse.map { it }
    }

    internal fun fetchNewDataOnRefresh() {
        weatherRepository.getTask(this)

    }
}




