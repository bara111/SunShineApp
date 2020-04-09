package com.example.weatherapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.Event
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.data.models.ApiResponse
import com.example.weatherapp.data.models.WeatherResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class MainViewModel @Inject constructor(
    weatherRepository: WeatherRepository
) : ViewModel() {
    private val disposable = CompositeDisposable()

    private var _weatherDailyData: MutableLiveData<ApiResponse<WeatherResponse, Throwable>> =
        MutableLiveData()
    val weatherDailyDataList: LiveData<ApiResponse<WeatherResponse, Throwable>> get() = _weatherDailyData

    private var _isLoadingProgressBar: MutableLiveData<Boolean> = MutableLiveData(true)
    val isLoadingProgressBar: LiveData<Boolean> get() = _isLoadingProgressBar

    private var _firstItemMaxTemp: MutableLiveData<Event<String?>> = MutableLiveData()
    val firstItemMaxTemp: LiveData<Event<String?>> get() = _firstItemMaxTemp

    private var _error: MutableLiveData<Event<Throwable>> = MutableLiveData()
    val error: LiveData<Event<Throwable>> get() = _error


    init {
        disposable.add(
            weatherRepository.weatherRemoteDataSource.requestData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    it
                }
                .subscribe(::onWeatherDataSuccess, ::onError)
        )
    }

    private fun onWeatherDataSuccess(weatherResponse: Response<WeatherResponse>) {
        _weatherDailyData.value = ApiResponse(weatherResponse.body(), null)
        _firstItemMaxTemp.value =
            Event(weatherResponse.body()?.list?.get(0)?.main?.converterTempMax())
        _isLoadingProgressBar.value = false
    }

    private fun onError(throwable: Throwable) {
        _weatherDailyData.value = ApiResponse(null, throwable)
        _error.value = Event(throwable)
        _isLoadingProgressBar.value = true
    }

    internal fun fetchNewDataOnRefresh() {
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}




