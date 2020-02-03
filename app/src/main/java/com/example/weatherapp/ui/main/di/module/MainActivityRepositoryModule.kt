package com.example.weatherapp.ui.main.di.module

import com.example.weatherapp.data.WeatherDataSource
import com.example.weatherapp.data.WeatherRepository
import dagger.Binds
import dagger.Module

@Module
abstract class MainActivityRepositoryModule {
    @Binds
    abstract fun provideMainActivityRepository(weatherRepository: WeatherRepository): WeatherDataSource
}