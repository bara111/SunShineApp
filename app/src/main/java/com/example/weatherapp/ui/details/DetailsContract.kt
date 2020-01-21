package com.example.weatherapp.ui.details

import com.example.weatherapp.database.WeatherEntity

class DetailsContract {
    interface Presenter{
        fun addRecord(weatherEntity: WeatherEntity)

    }
    interface View{

    }
}