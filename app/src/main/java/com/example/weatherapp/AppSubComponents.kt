package com.example.weatherapp

import com.example.weatherapp.ui.details.DetailsComponent
import com.example.weatherapp.ui.weatherrecords.WeatherDatabaseComponent
import dagger.Module

@Module(
    subcomponents = [
        DetailsComponent::class, WeatherDatabaseComponent::class
    ]
)
class AppSubComponents