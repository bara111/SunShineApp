package com.example.weatherapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao {

    @Query("SELECT * from weather_table")
    fun getRecords(): List<WeatherEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insert(weatherEntity: WeatherEntity)

    @Query("DELETE FROM weather_table")
     fun deleteAll()
}