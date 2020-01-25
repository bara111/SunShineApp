package com.example.weatherapp.ui.weather

import javax.inject.Inject

data class ItemViewModel @Inject constructor(
    val time: String,
    val maxTemp: String,
    val minTemp: String
)