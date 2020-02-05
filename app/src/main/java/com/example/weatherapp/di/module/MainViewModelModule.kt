package com.example.weatherapp.di.module

import androidx.lifecycle.ViewModel
import com.example.weatherapp.di.scope.ViewModelKey
import com.example.weatherapp.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
}