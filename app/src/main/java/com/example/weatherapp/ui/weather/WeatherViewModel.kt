package com.example.weatherapp.ui.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.data.local.WeatherEntity
import javax.inject.Inject

class WeatherViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var weatherRepository: WeatherRepository
    lateinit var weatherDataBaseList: LiveData<List<WeatherEntity>>
    fun getRecords(){
        weatherDataBaseList=weatherRepository.getRecords()
    }
}