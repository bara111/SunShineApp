package com.example.weatherapp.ui.weatherrecords
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class WeatherRecordsModule {
    @Singleton
    @Binds
    abstract fun provideWeatherRecordsPresenter(weatherRecordsActivityPresenter: WeatherRecordsActivityPresenter): WeatherRecordsContract.Presenter
}