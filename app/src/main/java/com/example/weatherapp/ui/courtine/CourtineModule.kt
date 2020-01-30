package com.example.weatherapp.ui.courtine

import dagger.Binds
import dagger.Module

@Module
abstract class CourtineModule {
    @Binds
    abstract fun provideDetailsPresenter(courtineActivityPresenter: CourtineActivityPresenter): CourtineContract.Presenter
}