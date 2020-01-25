package com.example.weatherapp.ui.weatherrecords

import com.example.weatherapp.data.database.WeatherEntity

class WeatherRecordsContract {
    interface Presenter {
        fun openDatabase()
    }


    interface View {
        fun updateViewData(list: List<WeatherEntity>)
    }
}