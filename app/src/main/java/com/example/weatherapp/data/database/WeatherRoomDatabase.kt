package com.example.weatherapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WeatherEntity::class], version = 1, exportSchema = false)
abstract class WeatherRoomDatabase : RoomDatabase() {
    abstract fun wordDao(): WeatherDao
    companion object {
        @Volatile
        private var INSTANCE: WeatherRoomDatabase? = null
        fun getDatabase(context: Context): WeatherRoomDatabase {
            val tempInstance = this.INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherRoomDatabase::class.java,
                    "word_database"
                ).allowMainThreadQueries().build()
                this.INSTANCE = instance
                return instance
            }
        }
    }
}