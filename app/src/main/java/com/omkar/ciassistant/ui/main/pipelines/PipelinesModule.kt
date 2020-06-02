package com.omkar.ciassistant.ui.main.pipelines

import androidx.lifecycle.ViewModel
import com.omkar.core.di.FragmentScoped
import com.omkar.core.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class PipelinesModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributePipelinesFragment(): PipelinesFragment

    @Binds
    @IntoMap
    @ViewModelKey(PipelinesViewModel::class)
    internal abstract fun bindRecentBuildsViewModel(viewModel: PipelinesViewModel): ViewModel
}
