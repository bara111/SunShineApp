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
    private val view: WeatherRecordsContract.View = this
    private lateinit var adapter: WeatherDatabaseAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BaseApp).appComponent.weatherDatabaseComponent().create().inject(this)

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityWeatherDatabaseBinding>(
            this,
            R.layout.activity_weather_database
        ).apply {
            lifecycleOwner = this@WeatherRecordsActivity
        }
        setSupportActionBar(binding.toolbarAll)
        weatherRecordsActivityPresenter.apply {
            setView(view)
            openDatabase()
        }
    }
    override fun updateViewData(list: List<WeatherEntity>) {
        with(binding) {
            adapter = WeatherDatabaseAdapter()
            adapter.submitList(list)
            recycleviewAll.hasFixedSize()
            recycleviewAll.adapter=adapter
        }
    }
}
