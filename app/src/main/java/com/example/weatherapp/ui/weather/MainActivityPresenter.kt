package com.example.weatherapp.ui.weather

import android.content.Context
import android.util.Log
import com.example.weatherapp.constants.Constants

import com.example.weatherapp.models.WeatherResponse
import com.example.weatherapp.network.WeatherService
import com.example.weatherapp.models.WeatherDailyData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainActivityPresenter @Inject constructor(var context: Context) : MainContract.Presenter {

    @Singleton
    @Inject
    lateinit var retrofit: Retrofit
    private var weatherResponse: List<WeatherDailyData>? = null
    lateinit var view: MainContract.View
    private lateinit var latNablus: String
    private lateinit var lonNablus: String
    private lateinit var apiKey: String
    override fun requestData() {
        val service = retrofit.create(WeatherService::class.java)
        val call = service.getCurrentWeatherData(
            latNablus,
            lonNablus, apiKey
        )
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.code() == 200) {
                    weatherResponse = response.body()?.list
                    view.updateViewData()
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.d(Constants.TAG, t.toString() + "")
            }
        })

    }

    override fun getMaxTemp(): String? {
        return weatherResponse?.get(0)?.main?.converterTempMax()
    }

    override fun getMinTemp(): String? {
        return weatherResponse?.get(0)?.main?.converterTempMin()
    }

    override fun getDescription(): String? {
        return weatherResponse!![0].weather[0].description
    }

    override fun getWeatherDaily(): List<WeatherDailyData>? {
        return weatherResponse
    }

    override fun getUrl(): String {
        return weatherResponse?.get(0)?.weather!![0].getUrl()
    }

    override fun setValues(
        view: MainContract.View,
        latNablus: String,
        lonNablus: String,
        apiKey: String
    ) {
        this.view=view
        this.latNablus=latNablus
        this.lonNablus=lonNablus
        this.apiKey=apiKey
    }

    override fun subscribe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unsubscribe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun attach(view: MainContract.View) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}




