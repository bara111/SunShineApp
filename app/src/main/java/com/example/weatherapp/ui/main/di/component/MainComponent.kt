package com.example.weatherapp.ui.main.di.component
import com.example.weatherapp.ui.main.MainActivity
import com.example.weatherapp.ui.main.di.module.MainActivityRepositoryModule
import com.example.weatherapp.ui.main.di.module.MainActivityViewModelModule
import dagger.Subcomponent
@Subcomponent(modules = [MainActivityViewModelModule::class, MainActivityRepositoryModule::class])
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(activity: MainActivity)
}