package com.example.weatherapp.data.remote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.data.models.WeatherDailyData
import com.example.weatherapp.data.models.WeatherResponse
import com.example.weatherapp.ui.main.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

class WeatherRemoteDataSource @Inject constructor() :
    WeatherDataSource.Remote {
    @Singleton
    @Inject
    lateinit var retrofit: Retrofit
    var weatherDailyDataList = MutableLiveData<List<WeatherDailyData>>()
    fun requestData() {
        val service = retrofit.create(WeatherService::class.java)
        val call = service.getCurrentWeatherData(
            "32.22",
            "35.22", "829740af696dc0258609d2d0a6a8472a"
        )
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.code() == 200 && response.body()?.list != null) {
                    val data: List<WeatherDailyData> = response.body()?.list!!
                    weatherDailyDataList.value = data
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.d(MainActivity.TAG, t.toString())
            }
        })
    }

    override fun getResponse(): MutableLiveData<List<WeatherDailyData>> {
        return weatherDailyDataList
    }
}

