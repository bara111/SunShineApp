package com.example.weatherapp.ui.weather.di.module

import androidx.lifecycle.ViewModel
import com.example.weatherapp.ui.weather.WeatherViewModel
import dagger.Binds
import dagger.Module
@Module
abstract class WeatherModule {
    @Binds
    abstract fun provideMainActivityPresenter(weatherViewModel: WeatherViewModel): ViewModel
}