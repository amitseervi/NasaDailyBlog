package com.amit.nasablog.di.modules

import com.amit.nasablog.di.components.MainActivityComponent
import dagger.Module

@Module(subcomponents = [MainActivityComponent::class], includes = [FragmentBuilder::class])
abstract class MainActivityModule