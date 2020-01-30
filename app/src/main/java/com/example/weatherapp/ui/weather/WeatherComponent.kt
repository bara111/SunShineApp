package com.example.weatherapp.ui.weather

import com.example.weatherapp.di.scope.ActivityScope
import dagger.Subcomponent
@ActivityScope
@Subcomponent(modules = [WeatherModule::class])
interface WeatherComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): WeatherComponent
    }
    fun inject(activity: WeatherActivity)
}