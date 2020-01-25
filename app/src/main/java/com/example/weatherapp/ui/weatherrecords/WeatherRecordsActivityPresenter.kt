package com.example.weatherapp.ui.weatherrecords

import android.content.Context
import com.example.weatherapp.data.database.WeatherDao
import com.example.weatherapp.data.database.WeatherEntity
import com.example.weatherapp.data.database.WeatherRoomDatabase

class WeatherRecordsActivityPresenter(
    context: Context,var view:WeatherRecordsContract.View
) : WeatherRecordsContract.Presenter {
    private var weatherRoomDatabase = WeatherRoomDatabase.getDatabase(context)
    private var dao: WeatherDao
    private lateinit var weatherRecords: List<WeatherEntity>

    init {
        dao = weatherRoomDatabase.wordDao()
    }

    override fun openDatabase() {
        weatherRecords = dao.getRecords()
        this.view.updateViewData(this.weatherRecords)

    }
}