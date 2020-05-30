package com.omkar.ciassistant.di

import com.omkar.ciassistant.MainApplication
import com.omkar.core.di.CoreDataModule
import com.omkar.core.di.CoroutinesModule
import com.omkar.core.di.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        CoreDataModule::class,
        CoroutinesModule::class,
        ViewModelModule::class,
        ActivityBindingModule::class
    ]
)
interface AppComponent : AndroidInjector<MainApplication> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<MainApplication>
}