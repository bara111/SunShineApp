package com.example.weatherapp.data.local

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_table")
data class WeatherEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "time") val time: String,
    @ColumnInfo(name = "MaxTemp") val MaxTemp: String?,
    @ColumnInfo(name = "MinTemp") val MinTemp: String?
)