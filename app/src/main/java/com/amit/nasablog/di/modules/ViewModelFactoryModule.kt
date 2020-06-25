package com.amit.navitest.di.modules

import androidx.lifecycle.ViewModelProvider
import com.amit.nasablog.ui.NasaBlogAppViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelProvider: NasaBlogAppViewModelProvider): ViewModelProvider.Factory
}