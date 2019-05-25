package com.wei.challenge.cartrack.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wei.challenge.cartrack.network.UsersApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber


class UserViewModel : ViewModel() {

    private var userList: MutableLiveData<List<User>>? = null
    private var disposable: Disposable? = null


    fun getUsers(): MutableLiveData<List<User>>? {
        if (userList == null) {
            userList = MutableLiveData()
            loadUsers()
        }
        return userList
    }


    private fun loadUsers() {

        disposable = UsersApi.create().getUsers().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    Timber.d(result.toString())
                    userList?.value = result
                },
                { error -> Timber.e("ERROR:" + error.message) }
            )

    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }



}