package com.example.weatherapp.ui.main.di.component
import com.example.weatherapp.ui.main.MainActivity
import com.example.weatherapp.ui.main.di.module.MainActivityRepositoryModule
import com.example.weatherapp.ui.main.di.module.MainActivityViewModelModule
import com.example.weatherapp.ui.main.di.module.WeatherRemoteDataSourceModule
import dagger.Subcomponent
@Subcomponent(modules = [MainActivityViewModelModule::class, MainActivityRepositoryModule::class, WeatherRemoteDataSourceModule::class])
interface MainActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainActivityComponent
    }
    fun inject(activity: MainActivity)
}