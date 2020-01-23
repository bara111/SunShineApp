package com.example.weatherapp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class City(
    @SerializedName("coord")
    val coordinates: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val sunrise: Int,
    val sunset: Int,
    val timezone: Int
):Serializable