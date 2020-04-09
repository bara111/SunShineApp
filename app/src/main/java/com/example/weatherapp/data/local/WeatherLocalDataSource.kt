package com.example.weatherapp.data.local

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.weatherapp.data.remote.WeatherDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherLocalDataSource @Inject constructor(
    var context: Context
) : WeatherDataSource.Local {
    private var daoDatabase: WeatherDao = WeatherRoomDatabase.getDatabase(context).wordDao()
    override suspend fun addDailyRecordToDataBase(weatherEntity: WeatherEntity) {
        withContext(Dispatchers.IO) {
            daoDatabase.addDailyRecordToDataBase(weatherEntity)
        }
    }

    override fun getDailyRecordsFromDataBase(): LiveData<List<WeatherEntity>> {
        return daoDatabase.getDailyRecordsFromDataBase()
    }
}