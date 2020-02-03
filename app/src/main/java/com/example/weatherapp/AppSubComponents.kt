package com.example.weatherapp

import com.example.weatherapp.ui.details.di.component.DetailsComponent
import com.example.weatherapp.ui.main.di.component.MainComponent
import com.example.weatherapp.ui.weather.di.component.WeatherComponent
import dagger.Module

@Module(
    subcomponents = [
        DetailsComponent::class, WeatherComponent::class, MainComponent::class
    ]
)
class AppSubComponents