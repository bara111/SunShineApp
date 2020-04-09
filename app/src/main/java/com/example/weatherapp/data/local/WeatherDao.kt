package com.example.weatherapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao {

    @Query("SELECT * from weather_table")
    fun getDailyRecordsFromDataBase(): LiveData<List<WeatherEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addDailyRecordToDataBase(weatherEntity: WeatherEntity)

    @Query("DELETE FROM weather_table")
    fun deleteAllRecordFromDataBase()
}