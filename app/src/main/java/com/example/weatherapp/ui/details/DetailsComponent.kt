package com.example.weatherapp.ui.details

import com.example.weatherapp.di.scope.ActivityScope
import dagger.Subcomponent
@ActivityScope
@Subcomponent(modules = [DetailsModule::class])
interface DetailsComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): DetailsComponent
    }

    fun inject(activity: DetailsActivity)
}