package com.example.weatherapp.di.component

import android.content.Context
import com.example.weatherapp.AppSubComponents
import com.example.weatherapp.di.module.RetrofitModule
import com.example.weatherapp.ui.details.di.component.DetailsComponent
import com.example.weatherapp.ui.main.di.component.MainComponent
import com.example.weatherapp.ui.weather.di.component.WeatherComponent
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
    fun mainComponent(): MainComponent.Factory
    fun detailsComponent(): DetailsComponent.Factory
    fun weatherDatabaseComponent(): WeatherComponent.Factory

}