package com.example.weatherapp.ui.details

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.weatherapp.data.database.DatabaseViewModel
import com.example.weatherapp.data.database.WeatherEntity

class DetailsActivityPresenter(viewModelStoreOwner: ViewModelStoreOwner):DetailsContract.Presenter {
    private var wordViewModel: DatabaseViewModel = ViewModelProvider(viewModelStoreOwner).get(DatabaseViewModel::class.java)
    private lateinit var weatherEntity:WeatherEntity
    override fun addRecord(time: String,maxTemp: String,minTemp:String) {
        wordViewModel.insert(weatherEntity)
    }
}