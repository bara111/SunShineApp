package com.example.weatherapp

import com.example.weatherapp.ui.details.DetailsComponent
import dagger.Module

@Module(
    subcomponents = [
        DetailsComponent::class
    ]
)
class AppSubcomponents