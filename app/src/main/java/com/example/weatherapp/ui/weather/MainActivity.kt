package com.example.weatherapp.ui.weather

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.BaseApp
import com.example.weatherapp.R
import com.example.weatherapp.constants.Constants
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.ui.details.DetailsActivity
import com.example.weatherapp.ui.weatherrecords.WeatherRecordsActivity
import javax.inject.Inject
import javax.inject.Singleton


class MainActivity : AppCompatActivity(),
    MainContract.View {
    @Singleton
    @Inject
     lateinit var mainActivityPresenter: MainActivityPresenter
    private lateinit var binding: ActivityMainBinding
    val TAG: String = MainActivity::javaClass.name

    override fun onCreate(savedInstanceState: Bundle?) {

        (application as BaseApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        binding.lifecycleOwner = this
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
        binding.progressCircular.visibility = View.GONE
        binding.WeatherRV.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.WeatherRV.hasFixedSize()
        binding.conditionTv.text = mainActivityPresenter.getDescription()
        binding.todayHighTempTv.text = mainActivityPresenter.getMaxTemp()
        binding.todayLowTempTv.text = mainActivityPresenter.getMinTemp()

        binding.WeatherRV.adapter =
            WeatherRecycleViewAdapter(
                mainActivityPresenter.getWeatherDaily()
            ) {
                var myIntent = Intent(this, DetailsActivity::class.java)
                myIntent.putExtra(Constants.DETAILDATA,it)
                startActivity(myIntent)
            }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.records -> {
                startActivity(Intent(this,WeatherRecordsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
