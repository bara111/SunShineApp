package com.example.weatherapp

import com.example.weatherapp.ui.details.DetailsComponent
import com.example.weatherapp.ui.weather.WeatherComponent
import dagger.Module

@Module(
    subcomponents = [
        DetailsComponent::class, WeatherComponent::class
    ]
)
class AppSubComponents