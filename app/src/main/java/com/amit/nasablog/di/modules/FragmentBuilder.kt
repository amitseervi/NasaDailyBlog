package com.amit.nasablog.di.modules

import com.alinus.superdm.di.scopes.FragmentScope
import com.amit.nasablog.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {
    @FragmentScope
    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun contributeCardListFragmentAndroidInjector(): HomeFragment
}