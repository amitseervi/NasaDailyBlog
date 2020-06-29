package com.amit.nasablog.di.modules

import com.alinus.superdm.di.scopes.FragmentScope
import com.amit.nasablog.ui.home.HomeFragment
import com.amit.nasablog.ui.preview.ImagePreviewFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributePreivewFragmentAndroidInjector(): ImagePreviewFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun contributeHomeFragmentAndroidInjector(): HomeFragment
}