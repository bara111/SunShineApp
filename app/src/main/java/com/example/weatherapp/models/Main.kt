package com.example.weatherapp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Main(
    val feels_like: Double,
    @SerializedName("grnd_level")
    val ground_level: Int,
    val humidity: Int,
    val pressure: Int,
    val sea_level: Int,
    val temp: Double,
    val temp_kf: Double,
    val temp_max: Double,
    val temp_min: Double
): Serializable {

    fun converterTempMin(): String {
        return ((this.temp_min - 273).toInt().toString() + "°")

    }

    fun converterTempMax(): String {

        return ((this.temp_max - 273).toInt().toString() + "°")
    }
    fun getHumidityWithUnit(): String {

        return this.humidity.toString()+"%"
    }
}