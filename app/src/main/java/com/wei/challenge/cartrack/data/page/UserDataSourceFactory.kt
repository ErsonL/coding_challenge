package com.wei.challenge.cartrack.data.page

import androidx.paging.DataSource
import com.wei.challenge.cartrack.model.User

class UserDataSourceFactory : DataSource.Factory<Long, User>() {
    override fun create(): DataSource<Long, User> = UserDataSource()
}