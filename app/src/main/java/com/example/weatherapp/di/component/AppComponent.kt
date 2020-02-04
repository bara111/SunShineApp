package com.example.weatherapp.di.component

import android.content.Context
import com.example.weatherapp.AppSubComponents
import com.example.weatherapp.di.module.RetrofitModule
import com.example.weatherapp.ui.details.DetailsActivity
import com.example.weatherapp.ui.main.MainActivity
import com.example.weatherapp.ui.weather.WeatherActivity

import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class, AppSubComponents::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(activity: WeatherActivity)
    fun inject(activity: DetailsActivity)

}