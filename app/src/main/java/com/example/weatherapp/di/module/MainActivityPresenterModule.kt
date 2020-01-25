package com.example.weatherapp.di.module

import com.example.weatherapp.ui.weather.MainActivityPresenter
import com.example.weatherapp.ui.weather.MainContract
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class MainActivityPresenterModule {
    @Singleton
    @Binds
    abstract fun provideMainActivityPresenter(mainActivityPresenter: MainActivityPresenter): MainContract.Presenter
}