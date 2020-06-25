package com.amit.nasablog.di.components

import com.amit.nasablog.di.modules.FragmentBuilder
import com.amit.nasablog.ui.MainActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [FragmentBuilder::class])
interface MainActivityComponent : AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    interface Builder {
        fun build(): MainActivityComponent
    }
}