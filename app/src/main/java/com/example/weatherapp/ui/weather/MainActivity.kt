package com.example.weatherapp.ui.weather

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.weatherapp.BaseApp
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.ui.details.DetailsActivity
import com.example.weatherapp.ui.weatherrecords.WeatherRecordsActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity(),
    MainContract.View {
    @Inject
    lateinit var mainActivityPresenter: MainActivityPresenter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BaseApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        ).apply { lifecycleOwner = this@MainActivity }
        setSupportActionBar(binding.toolbar)

        mainActivityPresenter.setValues(
            this,
            getString(R.string.lat_nablus),
            getString(R.string.lon_nablus),
            getString(R.string.api_key)
        )
        initView()
    }

    override fun initView() {
        mainActivityPresenter.requestData()
    }

    override fun updateViewData() {
        binding.apply {
            progressCircular.visibility = View.GONE
            WeatherRV.hasFixedSize()
            weatherData = mainActivityPresenter.getWeatherDaily()?.get(0)
            WeatherRV.adapter =
                WeatherRecycleViewAdapter(
                    mainActivityPresenter.getWeatherDaily()
                ) {
                    startActivity(DetailsActivity.newIntent(applicationContext, it))
                }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.records -> {
                this.startActivity(Intent(this, WeatherRecordsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        val TAG: String
            get() = MainActivity::javaClass.name
    }
}
