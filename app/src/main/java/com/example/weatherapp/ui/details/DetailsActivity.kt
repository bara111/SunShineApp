package com.example.weatherapp.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.weatherapp.R
import com.example.weatherapp.constants.Constants
import com.example.weatherapp.database.WeatherEntity
import com.example.weatherapp.databinding.ActivityDetailsBinding
import com.example.weatherapp.models.WeatherDailyData

class DetailsActivity : AppCompatActivity(),DetailsContract.View {
    private lateinit var detailsActivityPresenter: DetailsActivityPresenter
    private val TAG=DetailsActivity::class.java.simpleName
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var weatherDailyData: WeatherDailyData
    private lateinit var weatherEntity: WeatherEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        binding.lifecycleOwner = this

        setSupportActionBar(binding.toolbar)
        detailsActivityPresenter= DetailsActivityPresenter(this)
        weatherDailyData = intent.getSerializableExtra(Constants.DETAILDATA) as WeatherDailyData
        binding.degreeTv.text = weatherDailyData.wind.deg.toString()
        binding.speedTv.text = weatherDailyData.wind.speed.toString()
        binding.description.text = weatherDailyData.weather[0].description
        binding.maxTempTv.text = weatherDailyData.main.converterTempMax()
        binding.minTempTv.text = weatherDailyData.main.converterTempMin()
        binding.time.text = weatherDailyData.getFormatedTime()
        binding.humidityTv.text = weatherDailyData.main.getHumidityWithUnit()
        weatherEntity=WeatherEntity(weatherDailyData.getFormatedTime(),weatherDailyData.main.converterTempMax(),weatherDailyData.main.converterTempMin())

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.appbar, menu)
        return true
    }
        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.insert_data -> {
                    detailsActivityPresenter.addRecord(weatherEntity)
                    Toast.makeText(applicationContext,"added item",Toast.LENGTH_LONG).show()

                    true
                }
                else -> super.onOptionsItemSelected(item)
        }
    }
    }
