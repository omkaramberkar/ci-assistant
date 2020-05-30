package com.omkar.ciassistant

import androidx.lifecycle.ViewModel
import com.omkar.core.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LauncherModule {
    @Binds
    @IntoMap
    @ViewModelKey(LauncherViewModel::class)
    internal abstract fun bindLauncherViewModel(viewModel: LauncherViewModel): ViewModel
}