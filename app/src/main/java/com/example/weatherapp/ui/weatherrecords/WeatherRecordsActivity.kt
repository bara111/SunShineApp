package com.example.weatherapp.ui.weatherrecords

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.weatherapp.BaseApp
import com.example.weatherapp.R
import com.example.weatherapp.data.database.WeatherEntity
import com.example.weatherapp.databinding.ActivityWeatherDatabaseBinding
import javax.inject.Inject

class WeatherRecordsActivity : AppCompatActivity(), WeatherRecordsContract.View {
    @Inject
    lateinit var weatherRecordsActivityPresenter: WeatherRecordsActivityPresenter
    private lateinit var binding: ActivityWeatherDatabaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BaseApp).appComponent.weatherDatabaseComponent().create().inject(this)

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityWeatherDatabaseBinding>(
            this,
            R.layout.activity_weather_database
        ).apply {
            lifecycleOwner = this@WeatherRecordsActivity
        }
        setSupportActionBar(binding.toolbar)
        weatherRecordsActivityPresenter = WeatherRecordsActivityPresenter(applicationContext)
        weatherRecordsActivityPresenter.setView(this)
        weatherRecordsActivityPresenter.openDatabase()
    }

    override fun updateViewData(list: List<WeatherEntity>) {
        with(binding) {
            WeatherRV.hasFixedSize()
            WeatherRV.adapter = WeatherDatabaseRecycleViewAdapter(list) {}
        }
    }
}
