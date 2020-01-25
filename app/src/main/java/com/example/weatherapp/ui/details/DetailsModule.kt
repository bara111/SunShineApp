package com.example.weatherapp.ui.details
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DetailsModule {
    @Singleton
    @Binds
    abstract fun provideDetailsPresenter(detailsActivityPresenter: DetailsActivityPresenter): DetailsContract.Presenter
}