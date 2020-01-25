package com.example.weatherapp.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Rain(
    @SerializedName("`3h`")
    val snowValue: Double
): Serializable