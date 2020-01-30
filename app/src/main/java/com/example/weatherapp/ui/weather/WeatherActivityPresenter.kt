package com.example.weatherapp.ui.weather

import android.content.Context
import com.example.weatherapp.data.database.WeatherDao
import com.example.weatherapp.data.database.WeatherEntity
import com.example.weatherapp.data.database.WeatherRoomDatabase
import javax.inject.Inject

class WeatherActivityPresenter @Inject constructor(
    context: Context
) : WeatherContract.Presenter {
    private lateinit var view: WeatherContract.View
    private lateinit var weatherRecords: List<WeatherEntity>
    private var weatherRoomDatabase=WeatherRoomDatabase.getDatabase(context)
    private var dao: WeatherDao
    init {
        dao = weatherRoomDatabase.wordDao()
    }

    fun setView(view: WeatherContract.View) {
        this.view = view
    }

    override fun openDatabase() {
        weatherRecords = dao.getRecords()
        this.view.updateViewData(this.weatherRecords)

    }
}