package com.example.weatherapp.di.component

import android.content.Context
import com.example.weatherapp.AppSubComponents
import com.example.weatherapp.di.module.MainActivityPresenterModule
import com.example.weatherapp.di.module.RetrofitModule
import com.example.weatherapp.ui.details.DetailsComponent
import com.example.weatherapp.ui.weather.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

// Definition of the Application graph
@Singleton
@Component(modules = [MainActivityPresenterModule::class, RetrofitModule::class, AppSubComponents::class]) //tell the dagger to generate code with all dependencies it's called whenever you want to inject app graph in the activity
interface AppComponent {
    @Component.Factory // used to pass instances that already available at the time we are creating instance of the graph
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(mainActivity: MainActivity)//With the inject(mainActivity: MainActivity)method in the @Component interface, we're telling Dagger that MainActivity requests injection and that it has to provide what the Activity is injecting
    fun DetailsComponent(): DetailsComponent.Factory
}