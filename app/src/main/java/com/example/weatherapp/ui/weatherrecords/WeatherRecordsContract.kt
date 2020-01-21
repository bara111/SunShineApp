package com.example.weatherapp.ui.weatherrecords

import com.example.weatherapp.BaseContract
import com.example.weatherapp.database.WeatherEntity
import com.example.weatherapp.models.WeatherDailyData

class WeatherRecordsContract {
    interface Presenter {
        fun openDatabase()
    }


    interface View {
        fun updateViewData(list: List<WeatherEntity>)

    }


}