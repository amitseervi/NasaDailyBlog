package com.amit.nasablog.di.modules

import com.alinus.superdm.di.scopes.ActivityScope
import com.amit.nasablog.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesBuilder {
    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributeMainActivityAndroidInjector(): MainActivity
}