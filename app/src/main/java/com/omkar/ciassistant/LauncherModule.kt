package com.omkar.ciassistant

import android.content.res.Resources
import dagger.Module
import dagger.Provides

@Module
class LauncherModule {

    @Provides
    fun provideResources(activity: LauncherActivity): Resources {
        return activity.resources
    }
}
