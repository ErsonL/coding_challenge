package com.wei.challenge.cartrack.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wei.challenge.cartrack.db.AppDatabase
import com.wei.challenge.cartrack.db.UserDao
import com.wei.challenge.cartrack.model.User
import com.wei.challenge.cartrack.network.UsersApi
import com.wei.challenge.cartrack.utility.ioThread
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber


class UsersRepository private constructor(context: Context,private val userDao: UserDao) {

    private var userList: MutableLiveData<List<User>>? = null
    private var disposable: Disposable? = null

    fun getUsers(): LiveData<List<User>>? {
        if (userList == null || userList?.value?.size == 0) {
            userList = MutableLiveData()
            fromNetwork(userDao)
        }
        return userList
    }


    private fun fromNetwork(userDao: UserDao) {
        disposable = UsersApi.create().getUsers().subscribeOn(Schedulers.io())
            .subscribe(
                { result ->
                    Timber.d(result.toString())
                    userList?.postValue(result)
                    userDao.insertUsers(result)
                },
                { error ->
                    Timber.e("ERROR:" + error.message)
                    ioThread {
                        userList?.postValue(userDao.getAllUser())
                    }
                }
            )
    }

    fun destroy(){
        disposable?.dispose()
    }


    companion object {
        private var instance: UsersRepository? = null
        fun getInstance(context: Context): UsersRepository {
            if (instance == null) {
                instance = UsersRepository(context, AppDatabase.getInstance(context).userDao)
            }
            return instance!!
        }
    }
}