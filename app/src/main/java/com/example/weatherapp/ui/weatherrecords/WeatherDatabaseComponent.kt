package com.example.weatherapp.ui.weatherrecords

import dagger.Subcomponent

@Subcomponent(modules = [WeatherRecordsModule::class])
interface WeatherDatabaseComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): WeatherDatabaseComponent
    }

    fun inject(activity: WeatherRecordsActivity)
}