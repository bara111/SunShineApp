package com.example.weatherapp.data.remote

import android.content.Context
import com.example.weatherapp.R
import com.example.weatherapp.data.models.WeatherResponse
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class WeatherRemoteDataSource @Inject constructor(
    private var retrofit: Retrofit,
    private var context: Context
) : WeatherDataSource.Remote {
    lateinit var exception: java.lang.Exception
    suspend fun requestData(): Response<WeatherResponse>? {
        val service = retrofit.create(WeatherService::class.java)
        return try {
            val call = service.getCurrentWeatherData(
                context.resources.getString(R.string.retrofit_lat_nablus),
                context.resources.getString(R.string.retrofit_lon_nablus),
                context.resources.getString(R.string.retrofit_api_key)
            )
            call
        } catch (e: Exception) {
            exception = e
            null
        }
    }

}


