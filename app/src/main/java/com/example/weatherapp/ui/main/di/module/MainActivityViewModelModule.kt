package com.example.weatherapp.ui.main.di.module

import androidx.lifecycle.ViewModel
import com.example.weatherapp.ui.main.MainViewModel
import dagger.Binds
import dagger.Module

@Module
abstract class MainActivityViewModelModule {
    @Binds
    abstract fun provideMainActivityViewModel(mainViewModel: MainViewModel): ViewModel
}