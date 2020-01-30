package com.example.weatherapp.ui.weather

import com.example.weatherapp.data.database.WeatherEntity

class WeatherContract {
    interface Presenter {
        fun openDatabase()
    }

    interface View {
        fun updateViewData(list: List<WeatherEntity>)
    }
}