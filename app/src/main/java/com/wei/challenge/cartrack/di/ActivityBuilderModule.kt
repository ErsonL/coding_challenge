package com.wei.challenge.cartrack.di

import com.wei.challenge.cartrack.DetailActivity
import com.wei.challenge.cartrack.LoginActivity
import com.wei.challenge.cartrack.di.login.LoginModule
import com.wei.challenge.cartrack.di.login.LoginScope
import com.wei.challenge.cartrack.di.userList.DetailScope
import com.wei.challenge.cartrack.di.userList.UserListModule
import com.wei.challenge.cartrack.di.userList.UserListViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityBuildersModule {

    @LoginScope
    @ContributesAndroidInjector (modules = [
        LoginModule::class])
    abstract fun contributeLoginActivity(): LoginActivity

    @DetailScope
    @ContributesAndroidInjector
        (modules = [
            UserListModule::class,
            UserListViewModelModule::class])
    abstract fun contributeDetailActivity(): DetailActivity

}