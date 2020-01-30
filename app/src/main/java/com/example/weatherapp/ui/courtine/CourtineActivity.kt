package com.example.weatherapp.ui.courtine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.weatherapp.BaseApp
import com.example.weatherapp.R
import com.example.weatherapp.databinding.CourtineLayoutBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CourtineActivity : AppCompatActivity(), CourtineContract.View {
    @Inject
    lateinit var courtineActivityPresenter: CourtineActivityPresenter
    lateinit var binding: CourtineLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BaseApp).appComponent.courtineComponent().create().inject(this)
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.courtine_layout)
        courtineActivityPresenter.setView(this)
        CoroutineScope(Dispatchers.Main).launch {
            courtineActivityPresenter.fetchData()
        }
    }
    override fun updateUi(data: String) {
        binding.dataMainText.text = data
    }
}
