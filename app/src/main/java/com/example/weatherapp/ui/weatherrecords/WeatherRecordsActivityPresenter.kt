package com.example.weatherapp.ui.weatherrecords

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.weatherapp.data.database.DatabaseViewModel

class WeatherRecordsActivityPresenter(
    private val lifecycleOwner: LifecycleOwner,
    private val View: WeatherRecordsContract.View,
    viewModelStoreOwner: ViewModelStoreOwner
) : WeatherRecordsContract.Presenter {

    private var wordViewModel: DatabaseViewModel = ViewModelProvider(viewModelStoreOwner).get(
        DatabaseViewModel::class.java
    )


    override fun openDatabase() {
        wordViewModel.records.observe(lifecycleOwner, Observer { words ->
            words?.let {
                View.updateViewData(words)
            }
        })
    }
}