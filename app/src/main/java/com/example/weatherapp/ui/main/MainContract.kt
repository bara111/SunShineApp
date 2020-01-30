package com.example.weatherapp.ui.main

import com.example.weatherapp.data.models.WeatherDailyData

class MainContract {
    interface Presenter {
        fun requestData()
        fun getWeatherDaily(): List<WeatherDailyData>?
        fun setValues(
            view: View,
            latNablus: String,
            lonNablus: String,
            apiKey: String
        )
    }

    interface View {
        fun initView()
        fun updateViewData(list: ArrayList<WeatherDailyData>?)
    }


}