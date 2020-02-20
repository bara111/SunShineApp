package com.example.weatherapp.di.module

import androidx.lifecycle.ViewModel
import com.example.weatherapp.di.scope.ViewModelKey
import com.example.weatherapp.ui.weather.WeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class WeatherViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    abstract fun bindMainViewModel(weatherViewModel: WeatherViewModel): ViewModel
}