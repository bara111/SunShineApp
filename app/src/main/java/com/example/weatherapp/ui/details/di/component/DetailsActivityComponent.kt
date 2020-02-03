package com.example.weatherapp.ui.details.di.component

import com.example.weatherapp.ui.details.DetailsActivity
import com.example.weatherapp.ui.details.di.module.DetailsActivityViewModel
import dagger.Subcomponent

@Subcomponent (modules = [DetailsActivityViewModel::class])
interface DetailsActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): DetailsActivityComponent
    }

    fun inject(activity: DetailsActivity)
}