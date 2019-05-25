package com.wei.challenge.cartrack.network

import com.wei.challenge.cartrack.model.User
import com.wei.challenge.cartrack.utility.USERS_URL
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface UsersApi {

    @GET("users")
    fun getUsers(): Single<List<User>>

    companion object {

        fun create(): UsersApi {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(USERS_URL)
                .build()

            return retrofit.create(UsersApi::class.java)
        }
    }

}