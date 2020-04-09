package com.example.weatherapp.data.remote

import android.content.Context
import com.example.weatherapp.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherRemoteDataSource @Inject constructor(
    var weatherService: WeatherService,
    private var context: Context
) : WeatherDataSource.Remote {

    fun requestData() =
        weatherService.getFiveDaysWeatherData(
            context.resources.getString(R.string.retrofit_lat_nablus),
            context.resources.getString(R.string.retrofit_lon_nablus),
            context.resources.getString(R.string.retrofit_api_key)
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}




