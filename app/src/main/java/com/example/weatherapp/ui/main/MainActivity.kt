@file:Suppress("DEPRECATION")

package com.example.weatherapp.ui.main

import android.annotation.SuppressLint
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
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import leakcanary.AppWatcher
import javax.inject.Inject

@Suppress("PLUGIN_WARNING")
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter
    private lateinit var viewModel: MainViewModel
    @SuppressLint("MissingPermission")
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

        showErrorMessageNetworkRequest()
        showToastOnMaxTempChange()

        binding.swipeContainer?.setColorSchemeResources(
            android.R.color.holo_blue_bright
        )
        binding.swipeContainer?.setOnRefreshListener {
            fetchNewDataOnRefresh()
        }
        AppWatcher.objectWatcher.watch(this, "View was detached")

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

    private fun showToastOnMaxTempChange() {
        viewModel.maxTemp.observe(this, Observer { it ->
            it.getContentIfNotHandled()?.let {
                if (!it.contentEquals("null")) {
                    Snackbar.make(root_layout, it, Snackbar.LENGTH_SHORT).show()
                    binding.alertMain?.visibility = View.GONE
                    binding.textErrorMain?.visibility = View.GONE
                }
            }
        })
    }

    private fun showErrorMessageNetworkRequest() {
        viewModel.error.observe(this, Observer { it ->
            it.getContentIfNotHandled()?.let {
                if (!it.contentEquals("null")) {
                    Snackbar.make(root_layout, it, Snackbar.LENGTH_SHORT).show()
                    binding.progressbarMain.visibility = View.GONE
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
                textviewMainMaxtemp.text = list.getOrNull()?.list?.get(0)?.main?.converterTempMax()
                textviewMainMintemp.text = list.getOrNull()?.list?.get(0)?.main?.converterTempMin()
                textviewListitemCondition.text =
                    list.getOrNull()?.list?.get(0)?.weather?.get(0)?.description
            }
            mainAdapter.submitList(list.getOrNull()?.list)
        })
    }

    private fun fetchNewDataOnRefresh() {
        this.viewModel.fetchNewDataOnRefresh()
        binding.swipeContainer?.isRefreshing = false
    }
}
