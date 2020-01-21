package com.example.weatherapp.ui.weather

import com.example.weatherapp.BaseContract
import com.example.weatherapp.models.WeatherDailyData

class MainContract {
    interface Presenter: BaseContract.Presenter<View> {
        fun requestData()
        fun getMaxTemp(): String?
        fun getMinTemp(): String?
        fun getDescription(): String?
        fun getWeatherDaily(): List<WeatherDailyData>?
        fun getUrl(): String
        fun setValues(
            view: View,
            latNablus: String,
            lonNablus: String,
            apiKey: String
        )
    }


    interface View {
        fun initView()
        fun updateViewData()

    }


}