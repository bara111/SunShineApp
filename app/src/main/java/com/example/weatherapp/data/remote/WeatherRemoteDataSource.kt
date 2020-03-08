package com.example.weatherapp.data.remote

import android.content.Context
import com.example.weatherapp.R
import com.example.weatherapp.data.models.WeatherResponse
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import javax.inject.Inject

class WeatherRemoteDataSource @Inject constructor(
    private var retrofit: Retrofit,
    private var context: Context
) : WeatherDataSource.Remote {
    private var resultSuccess: WeatherResponse?=null
    private var resultFailure: java.lang.Exception?=null
    suspend fun requestData(): Boolean {
        var state: Boolean = false
        val service = retrofit.create(WeatherService::class.java)
        runBlocking {
            try {
                val call = service.getCurrentWeatherData(
                    context.resources.getString(R.string.retrofit_lat_nablus),
                    context.resources.getString(R.string.retrofit_lon_nablus),
                    context.resources.getString(R.string.retrofit_api_key)
                )
                if (!call.isSuccessful) {
                } else {
                    resultSuccess = call.body()
                    state = true
                }
            } catch (e: Exception) {
                resultFailure = e
                state = false
            }
        }
        return state
    }

    fun getSuccess(): WeatherResponse? {
        return resultSuccess
    }
    fun getException(): java.lang.Exception? {
        return resultFailure
    }
}


/*

    weatherResponse = response.body()!!
                    val result = Result.success(weatherResponse)

*/


/*                 val result = Result.failure<Nothing>(t)
*/