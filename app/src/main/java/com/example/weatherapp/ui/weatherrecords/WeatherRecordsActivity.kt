package com.example.weatherapp.ui.weatherrecords

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.weatherapp.R
import com.example.weatherapp.data.database.WeatherEntity
import com.example.weatherapp.databinding.ActivityWeatherDatabaseBinding

class WeatherRecordsActivity : AppCompatActivity(), WeatherRecordsContract.View {
    private lateinit var binding: ActivityWeatherDatabaseBinding
    private lateinit var weatherRecordsActivityPresenter: WeatherRecordsActivityPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityWeatherDatabaseBinding>(
            this,
            R.layout.activity_weather_database
        ).apply {
            lifecycleOwner = this@WeatherRecordsActivity
        }
        setSupportActionBar(binding.toolbar)
        weatherRecordsActivityPresenter = WeatherRecordsActivityPresenter(this, this, this)
        weatherRecordsActivityPresenter.openDatabase()
    }

    override fun updateViewData(list: List<WeatherEntity>) {
        with(binding) {
            WeatherRV.hasFixedSize()
            WeatherRV.adapter = WeatherDatabaseRecycleViewAdapter(list) {}
        }
    }
}
