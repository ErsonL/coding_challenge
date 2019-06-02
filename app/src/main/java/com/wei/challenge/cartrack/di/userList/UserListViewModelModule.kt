package com.wei.challenge.cartrack.di.userList

import androidx.lifecycle.ViewModel
import com.wei.challenge.cartrack.di.ViewModelKey
import com.wei.challenge.cartrack.model.UserListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class UserListViewModelModule {

    @Binds
    @DetailScope
    @IntoMap
    @ViewModelKey(UserListViewModel::class)
    abstract fun bindUserListViewModel(viewModel: UserListViewModel): ViewModel

}