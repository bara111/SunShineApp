package com.example.weatherapp.data.local

import androidx.lifecycle.LiveData
import com.example.weatherapp.data.WeatherDataSource
import com.example.weatherapp.data.models.WeatherDailyData
import com.example.weatherapp.data.models.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherLocalDataSource @Inject constructor() : WeatherDataSource {

    lateinit var weatherDao: WeatherDao
    override suspend fun saveRecord(weatherEntity: WeatherEntity) {
        withContext(Dispatchers.IO) {
            weatherDao.insert(weatherEntity)
        }
    }

    override suspend fun getRecords(): List<WeatherEntity> {
        return withContext(Dispatchers.IO) {
            weatherDao.getRecords()
        }
    }

    override  fun getResponse(): WeatherResponse? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}