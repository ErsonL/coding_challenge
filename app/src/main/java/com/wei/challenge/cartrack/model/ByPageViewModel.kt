package com.wei.challenge.cartrack.model

import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.wei.challenge.cartrack.data.page.UserDataSourceFactory

class ByPageViewModel : ViewModel() {
    val users = LivePagedListBuilder(
        UserDataSourceFactory(),
        PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(false).build()).build()
}

