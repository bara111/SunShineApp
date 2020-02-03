package com.example.weatherapp.ui.weather.di.component
import com.example.weatherapp.ui.weather.WeatherActivity
import dagger.Subcomponent

@Subcomponent
interface WeatherComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): WeatherComponent
    }

    fun inject(activity: WeatherActivity)
}