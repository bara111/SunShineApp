package com.example.weatherapp.ui.main

import androidx.lifecycle.*
import com.example.weatherapp.Event
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.data.models.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor(private var weatherRepository: WeatherRepository) :
    ViewModel() {
    private var _weatherDailyDataList: MutableLiveData<Result<WeatherResponse?>> =
        MutableLiveData()
    private var _errorResponse: MutableLiveData<Result<Nothing>> =
        MutableLiveData()
    val weatherDailyDataList: LiveData<Result<WeatherResponse?>> get() = _weatherDailyDataList
    var maxTemp: LiveData<Event<String>>
    var error: LiveData<Event<String>>
    var state:Boolean=false

    init {
        viewModelScope.launch(Dispatchers.IO) {
             state = weatherRepository.getTask()
            withContext(Dispatchers.Main) {
                if (state)
                _weatherDailyDataList.value=Result.success(weatherRepository.getSuccess())
                else {
                    _errorResponse.value= weatherRepository.getException()?.let { Result.failure(it) }
                }
            }
        }
        maxTemp = Transformations.map(_weatherDailyDataList) { response ->
            Event(
                "${response.getOrNull()?.list?.get(0)?.main?.converterTempMax()
                }"
            )
        }
        error = Transformations.map(_errorResponse) { response ->
            Event("${response.onFailure { it.cause }}}")
        }
    }


    internal fun fetchNewDataOnRefresh() {
        //weatherRepository.getTask()
    }
}




