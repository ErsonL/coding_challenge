package com.wei.challenge.cartrack.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.wei.challenge.cartrack.data.UsersRepository
import timber.log.Timber
import javax.inject.Inject


class UserListViewModel @Inject constructor(private val repository: UsersRepository) : ViewModel() {

    init {
        Timber.d( "UserListViewModel: viewmodel is working...")
    }


    fun getUsers(): LiveData<List<User>>? {
        return repository?.getUsers()
    }

    override fun onCleared() {
        super.onCleared()
        repository?.destroy()
    }

}