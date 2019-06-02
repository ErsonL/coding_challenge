package com.wei.challenge.cartrack.di

import androidx.lifecycle.ViewModelProvider
import com.wei.challenge.cartrack.viewmodel.ViewModelProviderFactory
import dagger.Binds
import dagger.Module


@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory): ViewModelProvider.Factory

}