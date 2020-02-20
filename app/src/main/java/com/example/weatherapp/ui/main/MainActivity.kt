@file:Suppress("DEPRECATION")

package com.example.weatherapp.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
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
import org.jetbrains.anko.toast
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter
    private lateinit var viewModel: MainViewModel
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
        with(binding) {
            recycleviewAll.adapter = mainAdapter
            recycleviewAll.hasFixedSize()
        }
        val viewModel by viewModels<MainViewModel> {
            viewModelFactory
        }
        this.viewModel = viewModel
        updateUI()
        showErrorMessageNetworkRequest(this)
        showToastOnMaxTempChange(this)
        binding.swipeContainer?.setColorSchemeResources(
            android.R.color.holo_blue_bright)

        binding.swipeContainer?.setOnRefreshListener {
            fetchNewDataOnRefresh()
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
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showToastOnMaxTempChange(context: Context) {
        viewModel.maxTemp.observe(this, Observer { it ->
            it.getContentIfNotHandled()?.let {
                if (!it.contentEquals("null")) {
                    context.toast(it)
                    binding.alertMain?.visibility = View.GONE
                    binding.textErrorMain?.visibility = View.GONE
                }
            }
        })
    }

    private fun showErrorMessageNetworkRequest(context: Context) {
        viewModel.error.observe(this, Observer { it ->
            it.getContentIfNotHandled()?.let {
                if (!it.contentEquals("null")) {
                    context.toast(it)
                    binding.alertMain?.visibility = View.VISIBLE
                    binding.textErrorMain?.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun updateUI() {
        this.viewModel.weatherDailyDataList.observe(this, Observer { list ->
            with(binding) {
                progressbarMain.visibility = View.GONE
                textviewMainMaxtemp?.text = list.Response?.list?.get(0)?.main?.converterTempMax()
                textviewMainMintemp?.text = list.Response?.list?.get(0)?.main?.converterTempMin()
                textviewListitemCondition.text =
                    list.Response?.list?.get(0)?.weather?.get(0)?.description
            }
            mainAdapter.submitList(list.Response?.list)
        })
    }

    private fun fetchNewDataOnRefresh() {
        this.viewModel.fetchNewDataOnRefresh()
        binding.swipeContainer?.isRefreshing = false

    }

}
