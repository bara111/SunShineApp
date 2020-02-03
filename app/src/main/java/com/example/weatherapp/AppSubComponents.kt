package com.example.weatherapp

import com.example.weatherapp.ui.details.di.component.DetailsActivityComponent
import com.example.weatherapp.ui.main.di.component.MainActivityComponent
import com.example.weatherapp.ui.weather.di.component.WeatherActivityComponent
import dagger.Module

@Module(
    subcomponents = [
        DetailsActivityComponent::class, WeatherActivityComponent::class, MainActivityComponent::class
    ]
)
class AppSubComponents