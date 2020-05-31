package com.omkar.ciassistant.di

import android.content.Context
import com.omkar.ciassistant.MainApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideContext(application: MainApplication): Context {
        return application.applicationContext
    }
}
