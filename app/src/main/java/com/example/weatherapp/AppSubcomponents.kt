package com.example.weatherapp

import android.content.Context
import com.example.weatherapp.di.component.AppComponent
import com.example.weatherapp.ui.details.DetailsActivity
import com.example.weatherapp.ui.details.DetailsComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module

@Module(
    subcomponents = [
        DetailsComponent::class
    ]
)
class AppSubcomponents