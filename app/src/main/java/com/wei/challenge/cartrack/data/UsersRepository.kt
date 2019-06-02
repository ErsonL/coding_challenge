package com.wei.challenge.cartrack.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wei.challenge.cartrack.db.UserDao
import com.wei.challenge.cartrack.model.User
import com.wei.challenge.cartrack.network.UsersApi
import com.wei.challenge.cartrack.utility.ioThread
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject


class UsersRepository @Inject constructor(private val usersApi: UsersApi, private val userDao: UserDao) {

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
        disposable = usersApi.getUsers().subscribeOn(Schedulers.io())
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

}