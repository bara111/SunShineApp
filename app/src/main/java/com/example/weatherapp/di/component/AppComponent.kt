package com.example.weatherapp.di.component

import android.content.Context
import com.example.weatherapp.AppSubComponents
import com.example.weatherapp.di.module.RetrofitModule
import com.example.weatherapp.ui.details.di.component.DetailsActivityComponent
import com.example.weatherapp.ui.main.di.component.MainActivityComponent
import com.example.weatherapp.ui.weather.di.component.WeatherActivityComponent
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

    fun mainComponent(): MainActivityComponent.Factory
    fun detailsComponent(): DetailsActivityComponent.Factory
    fun weatherDatabaseComponent(): WeatherActivityComponent.Factory

}