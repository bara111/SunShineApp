package com.example.weatherapp.ui.weather.di.module

import com.example.weatherapp.data.local.WeatherLocalDataSource
import com.example.weatherapp.data.remote.WeatherDataSource
import dagger.Binds
import dagger.Module

@Module
abstract class WeatherLocalDataSourceModule {
    @Binds
    abstract fun provideWeatherLocalDataSource(weatherLocalDataSource: WeatherLocalDataSource): WeatherDataSource.Local
}