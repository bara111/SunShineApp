package com.example.weatherapp.ui.details

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.weatherapp.database.DatabaseViewModel
import com.example.weatherapp.database.WeatherEntity

class DetailsActivityPresenter(viewModelStoreOwner: ViewModelStoreOwner):DetailsContract.Presenter {
    private var wordViewModel: DatabaseViewModel = ViewModelProvider(viewModelStoreOwner).get(DatabaseViewModel::class.java)
    override fun addRecord(weatherEntity: WeatherEntity) {
        wordViewModel.insert(weatherEntity)
    }
}