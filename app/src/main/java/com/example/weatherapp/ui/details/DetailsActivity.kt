package com.example.weatherapp.ui.details

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.weatherapp.BaseApp
import com.example.weatherapp.R
import com.example.weatherapp.data.database.WeatherEntity
import com.example.weatherapp.databinding.ActivityDetailsBinding
import com.example.weatherapp.data.models.WeatherDailyData

class DetailsActivity : AppCompatActivity(), DetailsContract.View {
    private lateinit var detailsActivityPresenter: DetailsActivityPresenter
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var weatherDailyData: WeatherDailyData

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BaseApp).appComponent.detailsComponent().create().inject(this)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        binding.lifecycleOwner = this
        setSupportActionBar(binding.toolbar)
        detailsActivityPresenter = DetailsActivityPresenter(this)
        weatherDailyData = intent.getSerializableExtra(EXTRA_DETAILS) as WeatherDailyData
        binding.weatherData = weatherDailyData
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.appbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.insert_data -> {
                detailsActivityPresenter.addRecord(
                    weatherDailyData.getFormatedTime(),
                    weatherDailyData.main.converterTempMax(),
                    weatherDailyData.main.converterTempMin()
                )
                Toast.makeText(applicationContext, "added item", Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    companion object {
        private val EXTRA_DETAILS: String = "${DetailsActivity::class.java.name} _DETAILS_EXTRA"
        fun newIntent(context: Context, weatherDailyData: WeatherDailyData): Intent {
            return Intent(context, DetailsActivity::class.java).putExtra(
                EXTRA_DETAILS,
                weatherDailyData
            )
        }
    }
}
