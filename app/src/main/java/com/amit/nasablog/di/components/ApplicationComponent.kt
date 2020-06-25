package com.amit.nasablog.di.components

import com.amit.nasablog.NasaBlogApp
import com.amit.nasablog.di.modules.ActivitiesBuilder
import com.amit.nasablog.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivitiesBuilder::class])
interface ApplicationComponent : AndroidInjector<NasaBlogApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(cameraApp: NasaBlogApp): Builder

        fun build(): ApplicationComponent
    }
}