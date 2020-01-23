package com.example.weatherapp.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class WeatherDailyData(
    val clouds: Clouds,
    @SerializedName("dt")
    val unixValue: Int,
    @SerializedName("dt_txt")
    val time: String,
    val main: Main,
    val rain: Rain,
    val sys: Sys,
    val weather: List<Weather>,
    val wind: Wind

) : Serializable {
    fun getFormatedTime(): String {

        return java.time.format.DateTimeFormatter.ISO_INSTANT
            .format(java.time.Instant.ofEpochSecond(unixValue.toLong()))
    }
}