package com.example.weatherapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.weatherapp.Event
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.data.models.ApiResponse
import com.example.weatherapp.data.models.WeatherResponse
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private var weatherRepository: WeatherRepository
) : ViewModel() {
    private val disposable = CompositeDisposable()
    private var _weatherDailyDataList: MutableLiveData<ApiResponse<WeatherResponse, String>> =
        weatherRepository.weatherRemoteDataSource.requestData(disposable)
    val weatherDailyDataList: LiveData<ApiResponse<WeatherResponse, String>> get() = _weatherDailyDataList
    var maxTemp: LiveData<Event<String>>
    var error: LiveData<Event<String>>

    init {
        maxTemp = Transformations.map(_weatherDailyDataList) { response ->
            Event("${response.Response?.list?.get(0)?.main?.converterTempMax()}")
        }
        error = Transformations.map(_weatherDailyDataList) { response ->
            Event("${response.error}")
        }
    }

    internal fun fetchNewDataOnRefresh() {
        _weatherDailyDataList = weatherRepository.weatherRemoteDataSource.requestData(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}




