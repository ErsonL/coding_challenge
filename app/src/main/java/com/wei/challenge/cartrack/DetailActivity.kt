package com.wei.challenge.cartrack

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wei.challenge.cartrack.network.UsersApi
import com.wei.challenge.cartrack.ui.UsersAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class DetailActivity : AppCompatActivity() {

    private val usersApi by lazy {
        UsersApi.create()
    }
    private var disposable: Disposable? = null

    private lateinit var usersList: RecyclerView
    private lateinit var usersAdapter: UsersAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setupRecyclerView()
        getUsers()

    }

    private fun getUsers() {
        disposable = usersApi.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    Timber.d(result.toString())
                    usersAdapter.setItems(result)
                },
                { error -> Timber.e("ERROR:"+ error.message) }
            )

    }

    private fun setupRecyclerView() {
        usersList = findViewById(R.id.users_list)
        usersList.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = RecyclerView.VERTICAL
        usersList.layoutManager = layoutManager
        usersAdapter = UsersAdapter {
            Timber.d("Ship:$it")
        }
        usersList.adapter = usersAdapter
    }

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, DetailActivity::class.java)
            context.startActivity(intent)
        }
    }
}
