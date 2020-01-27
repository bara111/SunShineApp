package com.example.weatherapp.di.module

import com.example.weatherapp.ui.weather.MainActivityPresenter
import com.example.weatherapp.ui.weather.MainContract
import dagger.Binds
import dagger.Module

@Module
abstract class MainActivityPresenterModule {
    @Binds
    abstract fun provideMainActivityPresenter(mainActivityPresenter: MainActivityPresenter): MainContract.Presenter
}