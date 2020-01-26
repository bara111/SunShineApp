package com.example.weatherapp.ui.weatherrecords

import com.example.weatherapp.di.scope.ActivityScope
import dagger.Subcomponent
@ActivityScope
@Subcomponent(modules = [WeatherRecordsModule::class])
interface WeatherDatabaseComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): WeatherDatabaseComponent
    }
    fun inject(activity: WeatherRecordsActivity)
}