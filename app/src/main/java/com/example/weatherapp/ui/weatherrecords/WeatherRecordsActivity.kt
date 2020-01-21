package com.example.weatherapp.ui.weatherrecords

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.database.WeatherEntity
import com.example.weatherapp.databinding.ActivityWeatherDatabaseBinding

class WeatherRecordsActivity : AppCompatActivity(), WeatherRecordsContract.View {
    private lateinit var binding: ActivityWeatherDatabaseBinding
    private lateinit var weatherRecordsActivityPresenter: WeatherRecordsActivityPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_weather_database)
        binding.lifecycleOwner = this
        setSupportActionBar(binding.toolbar)
        weatherRecordsActivityPresenter = WeatherRecordsActivityPresenter(this, this, this)
        weatherRecordsActivityPresenter.openDatabase()
    }
    override fun updateViewData(list: List<WeatherEntity>) {
        binding.WeatherRV.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.WeatherRV.hasFixedSize()
        binding.WeatherRV.adapter = WeatherDatabaseRecycleViewAdapter(list) {}
    }
}
