@file:Suppress("DEPRECATION")

package com.example.weatherapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.BaseApp
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.ui.details.DetailsActivity
import com.example.weatherapp.ui.weather.WeatherActivity
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BaseApp).appComponent.mainComponent().create().inject(this)
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
        val viewModel by viewModels<MainViewModel> {
            viewModelFactory
        }
        viewModel.weatherDailyDataList.observe(this, Observer { list ->
            with(binding) {
                progressbarMain.visibility = View.GONE
                textviewMainMaxtemp?.text = list.Response?.list?.get(0)?.main?.converterTempMax()
                textviewMainMintemp?.text = list.Response?.list?.get(0)?.main?.converterTempMin()
                textviewListitemCondition.text =
                    list.Response?.list?.get(0)?.weather?.get(0)?.description
            }
            mainAdapter.submitList(list.Response?.list)
        })
        viewModel.maxTemp.observe(this, Observer { it ->
            it.getContentIfNotHandled()?.let {
                if (!it.contentEquals("null"))
                    Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
        viewModel.error.observe(this, Observer { it ->
            it.getContentIfNotHandled()?.let {
                if (!it.contentEquals("null"))
                    Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })

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
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        val TAG: String = MainActivity::javaClass.name
    }
}
