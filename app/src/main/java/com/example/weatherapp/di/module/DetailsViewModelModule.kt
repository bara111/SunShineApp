package com.example.weatherapp.di.module

import androidx.lifecycle.ViewModel
import com.example.weatherapp.di.scope.ViewModelKey
import com.example.weatherapp.ui.details.DetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DetailsViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindMainViewModel(detailsViewModel: DetailsViewModel): ViewModel
}