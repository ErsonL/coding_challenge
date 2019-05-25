package com.wei.challenge.cartrack.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.wei.challenge.cartrack.app
import com.wei.challenge.cartrack.data.UsersRepository


class UserListViewModel constructor() : ViewModel() {

    private var repository: UsersRepository? = null

    init {
        repository = UsersRepository.getInstance(app)
    }

    fun getUsers(): LiveData<List<User>>? {
        return repository?.getUsers()
    }

    override fun onCleared() {
        super.onCleared()
        repository?.destroy()
    }


}