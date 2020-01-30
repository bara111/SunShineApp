package com.example.weatherapp.ui.weather
import dagger.Binds
import dagger.Module

@Module
abstract class WeatherModule {
    @Binds
    abstract fun provideWeatherRecordsPresenter(weatherRecordsActivityPresenter: WeatherActivityPresenter): WeatherContract.Presenter
}