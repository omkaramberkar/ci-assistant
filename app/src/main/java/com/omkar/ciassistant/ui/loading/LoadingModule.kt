package com.omkar.ciassistant.ui.loading

import androidx.lifecycle.ViewModel
import com.omkar.core.di.FragmentScoped
import com.omkar.core.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class LoadingModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeLoadingFragment(): LoadingFragment

    @Binds
    @IntoMap
    @ViewModelKey(LoadingViewModel::class)
    internal abstract fun bindLoadingViewModel(viewModel: LoadingViewModel): ViewModel
}
