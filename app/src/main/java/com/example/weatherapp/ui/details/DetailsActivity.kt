package com.example.weatherapp.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.weatherapp.BaseApp
import com.example.weatherapp.R
import com.example.weatherapp.data.models.WeatherDailyData
import com.example.weatherapp.databinding.ActivityDetailsBinding
import javax.inject.Inject

class DetailsActivity : AppCompatActivity(), DetailsContract.View {
    @Inject
    lateinit var detailsActivityPresenter: DetailsActivityPresenter
    private lateinit var binding: ActivityDetailsBinding
    private  var weatherDailyData: WeatherDailyData?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BaseApp).appComponent.detailsComponent().create().inject(this)
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView<ActivityDetailsBinding>(this, R.layout.activity_details)
                .apply {

                    lifecycleOwner = this@DetailsActivity
                    weatherDailyData = intent.getParcelableExtra(EXTRA_DETAILS)
                    weatherData = weatherDailyData
                }
        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.appbar, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.insert_data -> {
                detailsActivityPresenter.addRecord(
                    weatherDailyData!!.getFormatedTime(),
                    weatherDailyData!!.main!!.converterTempMax(),
                    weatherDailyData!!.main!!.converterTempMin()
                )
                Toast.makeText(applicationContext, "added item", Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private val EXTRA_DETAILS: String = "${DetailsActivity::class.java.name} _DETAILS_EXTRA"
        fun newIntent(context: Context, list: WeatherDailyData?): Intent {
            return Intent(context, DetailsActivity::class.java).putExtra(
                EXTRA_DETAILS,
                list
            )
        }
    }
}
