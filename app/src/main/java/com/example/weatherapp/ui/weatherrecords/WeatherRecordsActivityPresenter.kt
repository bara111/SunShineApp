package com.example.weatherapp.ui.weatherrecords

import android.content.Context
import com.example.weatherapp.data.database.WeatherDao
import com.example.weatherapp.data.database.WeatherEntity
import com.example.weatherapp.data.database.WeatherRoomDatabase
import javax.inject.Inject

class WeatherRecordsActivityPresenter @Inject constructor(
    context: Context
) : WeatherRecordsContract.Presenter {
    private lateinit var view: WeatherRecordsContract.View

    private var weatherRoomDatabase=WeatherRoomDatabase.getDatabase(context)
    private var dao: WeatherDao
    private lateinit var weatherRecords: List<WeatherEntity>

    init {
        dao = weatherRoomDatabase.wordDao()
    }

    fun setView(view: WeatherRecordsContract.View) {
        this.view = view
    }

    override fun openDatabase() {
        weatherRecords = dao.getRecords()
        this.view.updateViewData(this.weatherRecords)

    }
}