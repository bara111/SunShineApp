package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.di.component.AppComponent
import com.example.weatherapp.di.component.DaggerAppComponent



open class BaseApp : Application() {

    val appComponent: AppComponent by lazy {

        DaggerAppComponent.factory().create(applicationContext)
    }


}