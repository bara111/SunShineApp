package com.example.weatherapp.di.component

import android.content.Context
import com.example.weatherapp.AppSubComponents
import com.example.weatherapp.di.module.MainActivityPresenterModule
import com.example.weatherapp.di.module.RetrofitModule
import com.example.weatherapp.ui.courtine.CourtineComponent
import com.example.weatherapp.ui.details.DetailsComponent
import com.example.weatherapp.ui.main.MainActivity
import com.example.weatherapp.ui.weather.WeatherComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton
@Singleton
@Component(modules = [MainActivityPresenterModule::class, RetrofitModule::class, AppSubComponents::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
    fun inject(mainActivity: MainActivity)
    fun detailsComponent(): DetailsComponent.Factory
    fun weatherDatabaseComponent(): WeatherComponent.Factory
    fun courtineComponent(): CourtineComponent.Factory

}