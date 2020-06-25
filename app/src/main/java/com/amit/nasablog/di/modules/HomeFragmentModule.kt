package com.amit.nasablog.di.modules

import androidx.lifecycle.ViewModel
import com.amit.nasablog.di.scopes.ViewModelKey
import com.amit.nasablog.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeFragmentModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindCardListViewModel(viewModel: HomeViewModel): ViewModel
}