package com.example.weatherapp.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.ViewModelFactory
import com.example.weatherapp.data.local.WeatherLocalDataSource
import com.example.weatherapp.data.remote.WeatherDataSource
import com.example.weatherapp.data.remote.WeatherRemoteDataSource

import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    abstract fun bindWeatherLocalDataSource(weatherLocalDataSource: WeatherLocalDataSource): WeatherDataSource.Local

    @Binds
    abstract fun bindWeatherRemoteDataSource(weatherRemoteDataSource: WeatherRemoteDataSource): WeatherDataSource.Remote
}