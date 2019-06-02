package com.wei.challenge.cartrack.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.wei.challenge.cartrack.db.AppDatabase
import com.wei.challenge.cartrack.utility.BASE_URL
import com.wei.challenge.cartrack.utility.LOGIN_DB_NAME
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, LOGIN_DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

}