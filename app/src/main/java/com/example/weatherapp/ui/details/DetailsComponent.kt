package com.example.weatherapp.ui.details

import dagger.Subcomponent

@Subcomponent(modules = [DetailsModule::class])
interface DetailsComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): DetailsComponent
    }

    fun inject(activity: DetailsActivity)
}