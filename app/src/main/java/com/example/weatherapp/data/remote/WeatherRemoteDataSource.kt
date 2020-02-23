package com.example.weatherapp.data.remote

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.R
import com.example.weatherapp.data.models.ApiResponse
import com.example.weatherapp.data.models.ErrorMessage
import com.example.weatherapp.data.models.WeatherResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class WeatherRemoteDataSource @Inject constructor(
    var retrofit: Retrofit,
    private var context: Context
) : WeatherDataSource.Remote {
    private val disposable = CompositeDisposable()
    lateinit var error: ErrorMessage
    lateinit var weatherResponse: WeatherResponse
    lateinit var weatherApi: ApiResponse<WeatherResponse, ErrorMessage>
    var weatherService: WeatherService = retrofit.create(WeatherService::class.java)
    fun requestData():MutableLiveData<ApiResponse<WeatherResponse, ErrorMessage>> {
        disposable.add(
            weatherService.getCurrentWeatherData(
                context.resources.getString(R.string.retrofit_lat_nablus),
                context.resources.getString(R.string.retrofit_lon_nablus),
                context.resources.getString(R.string.retrofit_api_key)
            )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::onRetrievePostListSuccess)
        )
        return MutableLiveData(weatherApi)
    }

    private fun onRetrievePostListSuccess(weatherResponse: WeatherResponse) {
        Log.d("bara+++", weatherResponse.toString())
        this.weatherResponse = weatherResponse
        weatherApi = ApiResponse(weatherResponse, null)
    }

    private fun onRetrievePostListError(throwable: Throwable) {
        error = ErrorMessage(throwable.toString())

        weatherApi = ApiResponse(null, error)
    }
//
//    fun getData(): MutableLiveData<ApiResponse<WeatherResponse, ErrorMessage>> {
//        return MutableLiveData(weatherApi)
//    }
}

