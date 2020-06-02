package com.omkar.ciassistant.ui.main

import android.content.res.Resources
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun provideResources(activity: MainActivity): Resources {
        return activity.resources
    }
}
