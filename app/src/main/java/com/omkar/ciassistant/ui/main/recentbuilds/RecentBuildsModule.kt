package com.omkar.ciassistant.ui.main.recentbuilds

import androidx.lifecycle.ViewModel
import com.omkar.core.di.FragmentScoped
import com.omkar.core.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class RecentBuildsModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeRecentBuildsFragment(): RecentBuildsFragment

    @Binds
    @IntoMap
    @ViewModelKey(RecentBuildsViewModel::class)
    internal abstract fun bindRecentBuildsViewModel(viewModel: RecentBuildsViewModel): ViewModel
}
