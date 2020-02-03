package com.example.weatherapp.ui.details.di.module

import androidx.lifecycle.ViewModel
import com.example.weatherapp.ui.details.DetailsViewModel
import dagger.Binds
import dagger.Module
@Module
abstract class DetailsModule {
    @Binds
    abstract fun provideMainActivityPresenter(detailsViewModel: DetailsViewModel): ViewModel
}