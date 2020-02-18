package com.example.weatherapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.weatherapp.Event
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.data.models.WeatherResponse
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class MainViewModel @Inject constructor(weatherRepository: WeatherRepository) : ViewModel() {
    val lat:String="32.22"
    val lon:String="35.22"
    val API_KEY:String="829740af696dc0258609d2d0a6a8472a"
    var maxTemp: String?= null
    var data : LiveData<WeatherResponse?> = liveData(Dispatchers.IO) {
        val retrievedData = weatherRepository.requestData(lat,lon,API_KEY)
        emit(retrievedData)
        maxTemp= weatherRepository.weatherResponse?.list?.get(0)?.main?.converterTempMax()
        _errorEvent.postValue( Event(maxTemp))
    }
    var _errorEvent: MutableLiveData<Event<String?>> = MutableLiveData()

}