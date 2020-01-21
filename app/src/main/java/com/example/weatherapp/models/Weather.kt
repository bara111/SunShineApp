package com.example.weatherapp.models

import java.io.Serializable

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
): Serializable {

    fun getUrl():String{
        return "http://openweathermap.org/img/w/$icon.png"
    }
}