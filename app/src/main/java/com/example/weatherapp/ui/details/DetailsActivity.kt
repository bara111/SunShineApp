package com.example.weatherapp.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.BaseApp
import com.example.weatherapp.R
import com.example.weatherapp.data.models.WeatherDailyData
import com.example.weatherapp.databinding.ActivityDetailsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsActivity : AppCompatActivity() {
    lateinit var viewModel: DetailsViewModel
    private var weatherDailyData: WeatherDailyData? = null
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BaseApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView<ActivityDetailsBinding>(this, R.layout.activity_details)
                .apply {
                    lifecycleOwner = this@DetailsActivity
                }
        setSupportActionBar(binding.toolbarAll)
        weatherDailyData = intent.getParcelableExtra(EXTRA_DETAILS)
        binding.weatherData = weatherDailyData
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.appbar, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.insert_data -> {
                if (weatherDailyData?.main != null) {
                    CoroutineScope(Dispatchers.Main).launch {
                        viewModel.saveRecord(
                            weatherDailyData!!.getFormatedTime(),
                            weatherDailyData!!.main?.converterTempMax(),
                            weatherDailyData!!.main?.converterTempMin()
                        )
                    }
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    companion object {
        private val EXTRA_DETAILS: String = "${DetailsActivity::class.java.name}_DETAILS_EXTRA"
        fun newIntent(context: Context, list: WeatherDailyData?): Intent {
            return Intent(context, DetailsActivity::class.java).putExtra(
                EXTRA_DETAILS,
                list
            )
        }
    }
}
