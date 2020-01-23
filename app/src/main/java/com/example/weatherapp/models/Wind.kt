package com.example.weatherapp.models

import java.io.Serializable

data class Wind(
    val deg: Int,
    val speed: Double
): Serializable {
    fun getSpeedWithUnit():String{
        return this.speed.toString()+" m/s"
    }
    fun getDegreeWithUnit():String{
        return this.deg.toString()+"°"
    }
}