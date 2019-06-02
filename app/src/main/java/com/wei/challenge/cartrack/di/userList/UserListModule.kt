package com.wei.challenge.cartrack.di.userList

import com.wei.challenge.cartrack.db.AppDatabase
import com.wei.challenge.cartrack.db.UserDao
import com.wei.challenge.cartrack.network.UsersApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module
class UserListModule {

    @DetailScope
    @Provides
    fun provideUsersApi(retrofit: Retrofit): UsersApi {
        return retrofit.create<UsersApi>(UsersApi::class.java!!)
    }


    @DetailScope
    @Provides
    fun provideUserDao(db: AppDatabase): UserDao {
        return db.userDao
    }

}
