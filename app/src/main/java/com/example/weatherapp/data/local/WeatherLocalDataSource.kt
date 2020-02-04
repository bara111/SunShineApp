package com.example.weatherapp.data.local

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.weatherapp.data.remote.WeatherDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherLocalDataSource  (var context: Context) :
    WeatherDataSource.Local {
    private var daoDatabase: WeatherDao = WeatherRoomDatabase.getDatabase(context).wordDao()
    override suspend fun saveRecord(weatherEntity: WeatherEntity) {
        withContext(Dispatchers.IO) {
            daoDatabase.insert(weatherEntity)
        }
    }

    override fun getRecords(): LiveData<List<WeatherEntity>> {
        return daoDatabase.getRecords()
    }
}