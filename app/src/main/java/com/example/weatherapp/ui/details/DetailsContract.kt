package com.example.weatherapp.ui.details

class DetailsContract {
    interface Presenter {
        fun addRecord(time: String, maxTemp: String, minTemp: String)
    }

    interface View
}