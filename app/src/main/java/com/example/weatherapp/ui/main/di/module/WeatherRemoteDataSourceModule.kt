package com.example.weatherapp.ui.main.di.module

import com.example.weatherapp.data.remote.WeatherDataSource
import com.example.weatherapp.data.remote.WeatherRemoteDataSource
import dagger.Binds
import dagger.Module

@Module
abstract class WeatherRemoteDataSourceModule {
    @Binds
    abstract fun provideWeatherRemoteDataSource(weatherRemoteDataSource: WeatherRemoteDataSource): WeatherDataSource.Remote
}