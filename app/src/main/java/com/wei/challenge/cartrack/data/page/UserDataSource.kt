package com.wei.challenge.cartrack.data.page

import androidx.paging.PageKeyedDataSource
import com.wei.challenge.cartrack.model.User
import com.wei.challenge.cartrack.network.UsersApi
import com.wei.challenge.cartrack.utility.ExecuteOnceObserver
import io.reactivex.schedulers.Schedulers

class UserDataSource : PageKeyedDataSource<Long, User>() {

    override fun loadInitial(
        params: LoadInitialParams<Long>, callback:
        LoadInitialCallback<Long, User>
    ) {
        UsersApi.create().getUsers()
            .subscribeOn(Schedulers.io())
            .subscribe(ExecuteOnceObserver( onExecuteOnceNext = {
                callback.onResult(it, 0, 1)
            }))
    }

    override fun loadAfter(
        params: LoadParams<Long>, callback: LoadCallback<Long, User>
    ) {
        UsersApi.create().getUsers()
            .subscribeOn(Schedulers.io())
            .subscribe(ExecuteOnceObserver( onExecuteOnceNext = {
                callback.onResult(it, 0)
            }))
    }

    override fun loadBefore(
        params: LoadParams<Long>, callback: LoadCallback<Long,
                User>
    ) {

    }
}