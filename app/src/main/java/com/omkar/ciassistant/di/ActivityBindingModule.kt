package com.omkar.ciassistant.di

import com.omkar.ciassistant.LauncherActivity
import com.omkar.ciassistant.LauncherModule
import com.omkar.ciassistant.ui.loading.LoadingModule
import com.omkar.ciassistant.ui.main.MainActivity
import com.omkar.ciassistant.ui.main.MainModule
import com.omkar.ciassistant.ui.main.recentbuilds.RecentBuildsModule
import com.omkar.ciassistant.ui.registration.TokenRegistrationModule
import com.omkar.core.di.ActivityScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            // activity
            LauncherModule::class,
            // fragments
            LoadingModule::class,
            TokenRegistrationModule::class
        ]
    )
    internal abstract fun contributeLauncherActivity(): LauncherActivity

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            // activity
            MainModule::class,
            // fragments
            RecentBuildsModule::class
        ]
    )
    internal abstract fun mainActivity(): MainActivity
}
