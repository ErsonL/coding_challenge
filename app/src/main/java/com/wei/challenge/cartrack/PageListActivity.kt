package com.wei.challenge.cartrack

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wei.challenge.cartrack.data.page.UserPageAdapter
import com.wei.challenge.cartrack.model.ByPageViewModel
import com.wei.challenge.cartrack.ui.MarginItemDecoration


class PageListActivity : AppCompatActivity(){

    private lateinit var usersList: RecyclerView
    private lateinit var usersListAdapter: UserPageAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_list)

        setupRecyclerView()
        val mByPageViewModel = ViewModelProviders.of(this).get(ByPageViewModel::class.java)

        mByPageViewModel.users.observe(this,  Observer(usersListAdapter::submitList))

    }

    private fun setupRecyclerView() {
        usersList = findViewById(R.id.users_list)
        usersList.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = RecyclerView.VERTICAL
        usersList.addItemDecoration(MarginItemDecoration(resources.getDimension(R.dimen.default_padding).toInt()))
        usersList.layoutManager = layoutManager

        usersListAdapter = UserPageAdapter{}
        usersList.adapter = usersListAdapter

    }



    companion object {
        fun open(context: Context) {
            val intent = Intent(context, PageListActivity::class.java)
            context.startActivity(intent)
        }
    }
}
