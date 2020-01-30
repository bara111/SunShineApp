package com.example.weatherapp.ui.courtine

import com.example.weatherapp.di.scope.ActivityScope
import dagger.Subcomponent
@ActivityScope
@Subcomponent(modules = [CourtineModule::class])
interface CourtineComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): CourtineComponent
    }

    fun inject(activity: CourtineActivity)
}