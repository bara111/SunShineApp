package com.example.weatherapp.data.models
    data class WeatherResponse(
        val city: City?,
        val cnt: Int,
        val cod: String?,
        val list: List<WeatherDailyData>?,
        val message: Int
    )



