package com.example.weatherapp.ui.details

import android.content.Context
import com.example.weatherapp.data.database.WeatherDao
import com.example.weatherapp.data.database.WeatherEntity
import com.example.weatherapp.data.database.WeatherRoomDatabase
import javax.inject.Inject

class DetailsActivityPresenter @Inject constructor(var context: Context) : DetailsContract.Presenter {
    private val weatherRoomDatabase by lazy {WeatherRoomDatabase.getDatabase(context)}
    private var dao: WeatherDao

    init {
        this.dao = weatherRoomDatabase.wordDao()
    }

    override fun addRecord(time: String, maxTemp: String, minTemp: String) {
        dao.insert(WeatherEntity(time, maxTemp, minTemp))
    }
}