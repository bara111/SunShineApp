package com.example.weatherapp.ui.details

import dagger.Subcomponent

@Subcomponent
interface DetailsComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): DetailsComponent
    }

    fun inject(activity: DetailsActivity)
}