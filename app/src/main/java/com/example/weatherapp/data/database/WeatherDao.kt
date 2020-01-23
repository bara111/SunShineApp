package com.example.weatherapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao {

    @Query("SELECT * from weather_table")
    fun getRecords(): LiveData<List<WeatherEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(weatherEntity: WeatherEntity)

    @Query("DELETE FROM weather_table")
    suspend fun deleteAll()
}