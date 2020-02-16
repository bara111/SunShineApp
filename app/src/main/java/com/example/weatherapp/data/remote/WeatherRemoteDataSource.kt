package com.example.weatherapp.data.remote

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.R
import com.example.weatherapp.data.models.ApiResponse
import com.example.weatherapp.data.models.ErrorMessage
import com.example.weatherapp.data.models.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class WeatherRemoteDataSource @Inject constructor(
    private var retrofit: Retrofit,
    var context: Context
) : WeatherDataSource.Remote {
    var weatherApiMutable = MutableLiveData<ApiResponse<WeatherResponse, ErrorMessage>>()
    lateinit var weatherApi: ApiResponse<WeatherResponse, ErrorMessage>
    lateinit var error: ErrorMessage
    lateinit var weatherResponse: WeatherResponse
    fun requestData() {
        val service = retrofit.create(WeatherService::class.java)
        val call = service.getCurrentWeatherData(
            context.resources.getString(R.string.retrofit_lat_nablus),
            context.resources.getString(R.string.retrofit_lon_nablus),
            context.resources.getString(R.string.retrofit_api_key)
        )
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.code() == 200 && response.body()?.list != null) {
                    weatherResponse = response.body()!!
                    weatherApi = ApiResponse(weatherResponse, null)
                    weatherApiMutable.value = weatherApi
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                error = ErrorMessage(t.toString())
                weatherApi = ApiResponse(null, error)
                weatherApiMutable.value = weatherApi
            }
        })
    }

    override fun getResponse(): MutableLiveData<ApiResponse<WeatherResponse, ErrorMessage>> {
        return weatherApiMutable
    }
}

