package com.example.weatherapp.data.remote

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.R
import com.example.weatherapp.data.models.ApiResponse
import com.example.weatherapp.data.models.WeatherResponse
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class WeatherRemoteDataSource @Inject constructor(
    var weatherService: WeatherService,
    private var context: Context
) : WeatherDataSource.Remote {

    var apiResponse: MutableLiveData<ApiResponse<WeatherResponse, String>> = MutableLiveData()
    fun requestData(disposable: CompositeDisposable): MutableLiveData<ApiResponse<WeatherResponse, String>> {
        val observable: Single<Response<WeatherResponse>> =
            weatherService.getCurrentWeatherData(
                context.resources.getString(R.string.retrofit_lat_nablus),
                context.resources.getString(R.string.retrofit_lon_nablus),
                context.resources.getString(R.string.retrofit_api_key)
            )
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Response<WeatherResponse>> {
                override fun onSuccess(weatherResponse: Response<WeatherResponse>) {
                    apiResponse.value = ApiResponse(weatherResponse.body(), null)
                }

                override fun onSubscribe(d: Disposable) {
                    disposable.add(d)
                }

                override fun onError(throwable: Throwable) {
                    apiResponse.value = ApiResponse(null, throwable.message)
                }
            })
        return apiResponse
    }
}




