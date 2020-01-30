package com.example.weatherapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.weatherapp.BaseApp
import com.example.weatherapp.R
import com.example.weatherapp.data.models.WeatherDailyData
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.ui.courtine.CourtineActivity
import com.example.weatherapp.ui.details.DetailsActivity
import com.example.weatherapp.ui.weather.WeatherActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity(),
    MainContract.View {
    @Inject
    lateinit var mainActivityPresenter: MainActivityPresenter
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter
    private var weatherDataList: ArrayList<WeatherDailyData>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BaseApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        ).apply { lifecycleOwner = this@MainActivity }

        setSupportActionBar(binding.toolbarAll)
        mainAdapter = MainAdapter {
            startActivity(DetailsActivity.newIntent(this@MainActivity, it))
        }
        binding.recycleviewAll.adapter = mainAdapter
        binding.recycleviewAll.hasFixedSize()
        if (savedInstanceState == null) {
            mainActivityPresenter.setValues(
                this,
                getString(R.string.retrofit_lat_nablus),
                getString(R.string.retrofit_lon_nablus),
                getString(R.string.retrofit_api_key)
            )
            initView()
        } else {
            weatherDataList = savedInstanceState.getParcelableArrayList<WeatherDailyData>(lIST)
            onRotateDevice(weatherDataList)
        }
    }

    override fun initView() {
        mainActivityPresenter.requestData()
    }

    override fun updateViewData(list: ArrayList<WeatherDailyData>?) {
        with(binding) {
            progressbarMain.visibility = View.GONE
            weatherDataList = list
            weatherData = list?.get(0)
            mainAdapter.submitList(list)

        }
    }

    private fun onRotateDevice(list: ArrayList<WeatherDailyData>?) {
        with(binding) {
            progressbarMain.visibility = View.GONE
            mainAdapter.submitList(list)
            weatherData = list?.get(0)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.records -> {
                startActivity(Intent(this, WeatherActivity::class.java))
                true
            }
            R.id.courtine -> {
                startActivity(Intent(this, CourtineActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        return outState.putParcelableArrayList(lIST, weatherDataList)
    }

    companion object {
        val TAG: String = MainActivity::javaClass.name
        const val lIST: String = "SAVED_LIST"
    }
}
