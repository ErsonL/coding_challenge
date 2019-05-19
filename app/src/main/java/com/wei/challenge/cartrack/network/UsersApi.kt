package com.wei.challenge.cartrack.network

import com.wei.challenge.cartrack.model.User
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val URL = "https://jsonplaceholder.typicode.com/"

interface ApiClient {

    @GET("users")
    fun getUsers(): Observable<List<User>>

    companion object {

        fun create(): ApiClient {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .build()

            return retrofit.create(ApiClient::class.java)
        }
    }

}