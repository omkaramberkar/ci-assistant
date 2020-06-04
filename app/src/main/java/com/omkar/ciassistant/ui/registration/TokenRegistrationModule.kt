package com.omkar.ciassistant.ui.registration

import androidx.lifecycle.ViewModel
import com.omkar.core.di.FragmentScoped
import com.omkar.core.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class TokenRegistrationModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeTokenRegistrationFragment(): TokenRegistrationFragment

    @Binds
    @IntoMap
    @ViewModelKey(TokenRegistrationViewModel::class)
    internal abstract fun bindTokenRegistrationViewModel(viewModel: TokenRegistrationViewModel): ViewModel
}
