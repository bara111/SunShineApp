package com.example.weatherapp.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DatabaseViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: WeatherRepository
    val Records: LiveData<List<WeatherEntity>>

    init {
        val wordsDao = WeatherRoomDatabase.getDatabase(application).wordDao()
        repository = WeatherRepository(wordsDao)
        Records = repository.allWeatherRecord
    }
    fun insert(word: WeatherEntity) = viewModelScope.launch {
        repository.insert(word)
    }
}