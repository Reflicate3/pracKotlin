package com.example.sixthprackotlin

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object MyModule {

    @Provides
    fun provideMyDependency(): MyDependency {
        return MyDependency()
    }
}