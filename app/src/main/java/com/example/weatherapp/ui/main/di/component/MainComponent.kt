package com.example.weatherapp.ui.main.di.component

import com.example.weatherapp.ui.main.MainActivity
import dagger.Subcomponent

@Subcomponent
interface MainComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(activity: MainActivity)
}