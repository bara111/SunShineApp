package com.example.weatherapp.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.data.WeatherDataSource
import com.example.weatherapp.data.local.WeatherEntity
import com.example.weatherapp.data.models.WeatherDailyData
import com.example.weatherapp.data.models.WeatherResponse
import com.example.weatherapp.ui.main.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

class WeatherRemoteDataSource @Inject constructor() : WeatherDataSource {
    @Singleton
    @Inject
    lateinit var retrofit: Retrofit
    var weatherResponse: WeatherResponse? = null

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
                if (response.code() == 200) {
                    weatherResponse = response.body()
                    Log.d("retrofit+",weatherResponse.toString())

                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.d("retrofit+",weatherResponse.toString())

                Log.d(MainActivity.TAG, t.toString())
            }
        })
    }


    override suspend fun saveRecord(weatherEntity: WeatherEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getRecords(): List<WeatherEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getResponse(): WeatherResponse? {
        return weatherResponse
    }
}

