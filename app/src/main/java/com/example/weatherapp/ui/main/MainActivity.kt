package com.example.weatherapp.ui.main

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
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter
    private val viewModel by viewModels<MainViewModel> {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BaseApp).appComponent.mainComponent().create().inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        ).apply { lifecycleOwner = this@MainActivity }

        initViews()

        initViewModel()

    }

    private fun initViewModel() {
        with(viewModel){
            weatherDailyDataList.observe(this@MainActivity, Observer { list ->
                with(binding) {
                    progressbarMain.visibility = View.GONE
                    binding.weatherData=list.getOrNull()?.list?.get(0)
                }
                mainAdapter.submitList(list.getOrNull()?.list)
            })
        }


        viewModel.error.observe(this@MainActivity, Observer { it ->
            it.getContentIfNotHandled()?.let {
                if (!it.contentEquals("null")) {
                    Snackbar.make(binding.rootLayout, it, Snackbar.LENGTH_SHORT).show()
                    with(binding){
                        progressbarMain.visibility = View.GONE
                        alertMain.visibility = View.VISIBLE
                        textErrorMain.visibility = View.VISIBLE
                    }

                }
            }
        })

        viewModel.maxTemp.observe(this@MainActivity, Observer { it ->
            it.getContentIfNotHandled()?.let {
                if (!it.contentEquals("null")) {
                    Snackbar.make(binding.rootLayout, it, Snackbar.LENGTH_SHORT).show()
                    with(binding){
                        alertMain.visibility = View.GONE
                        textErrorMain.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun initViews() {
        setSupportActionBar(binding.toolbarAll)

        mainAdapter = MainAdapter {
            startActivity(DetailsActivity.newIntent(this@MainActivity, it))
        }

        with(binding.recycleviewAll) {
            adapter = mainAdapter
            hasFixedSize()
        }

        with(binding.swipeContainer) {
            setColorSchemeResources(
                android.R.color.holo_blue_bright
            )

            setOnRefreshListener {
                fetchNewDataOnRefresh()
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
                startActivity(Intent(this, WeatherActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun fetchNewDataOnRefresh() {
        viewModel.fetchNewDataOnRefresh()
        binding.swipeContainer.isRefreshing = false
    }
}
