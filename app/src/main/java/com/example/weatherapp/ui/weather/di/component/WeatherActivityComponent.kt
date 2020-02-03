package com.example.weatherapp.ui.weather.di.component

import com.example.weatherapp.ui.weather.WeatherActivity
import com.example.weatherapp.ui.weather.di.module.WeatherActivityViewModelModule
import com.example.weatherapp.ui.weather.di.module.WeatherLocalDataSourceModule
import dagger.Subcomponent

@Subcomponent(modules = [WeatherLocalDataSourceModule::class, WeatherActivityViewModelModule::class])
interface WeatherActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): WeatherActivityComponent
    }

    fun inject(activity: WeatherActivity)
}