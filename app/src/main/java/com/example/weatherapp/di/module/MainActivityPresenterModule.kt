package com.example.weatherapp.di.module

import android.content.Context
import com.example.weatherapp.ui.weather.MainActivityPresenter
import com.example.weatherapp.ui.weather.MainContract
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
   abstract class MainActivityPresenterModule {
    @Singleton

    @Binds
    abstract fun proivdeMainActivityPresenter(mainActivityPresenter: MainActivityPresenter): MainContract.Presenter

}