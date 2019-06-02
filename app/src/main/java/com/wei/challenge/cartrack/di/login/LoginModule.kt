package com.wei.challenge.cartrack.di.login

import com.wei.challenge.cartrack.db.AppDatabase
import com.wei.challenge.cartrack.db.LoginDao
import dagger.Module
import dagger.Provides


@Module
class LoginModule {

    @LoginScope
    @Provides
    fun provideLoginDao(db: AppDatabase): LoginDao {
        return db.loginDao
    }

}
