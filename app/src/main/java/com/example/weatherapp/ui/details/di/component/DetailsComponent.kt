package com.example.weatherapp.ui.details.di.component

import com.example.weatherapp.ui.details.DetailsActivity
import dagger.Subcomponent

@Subcomponent
interface DetailsComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): DetailsComponent
    }

    fun inject(activity: DetailsActivity)

}