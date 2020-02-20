package com.example.weatherapp.ui.weather

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.BaseApp
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityWeatherBinding
import javax.inject.Inject

class WeatherActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: ActivityWeatherBinding
    private lateinit var adapter: WeatherAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BaseApp).appComponent.weatherComponent().create().inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityWeatherBinding>(
            this,
            R.layout.activity_weather
        ).apply {
            lifecycleOwner = this@WeatherActivity
        }
        setSupportActionBar(binding.toolbarAll)
        adapter = WeatherAdapter()
        val viewModel by viewModels<WeatherViewModel> {
            viewModelFactory
        }
        viewModel.weatherDataBaseList.observe(this, Observer { list ->
            with(binding) {
                adapter.submitList(list)
                recycleviewAll.hasFixedSize()
                recycleviewAll.adapter = adapter
            }
        })
    }
}

