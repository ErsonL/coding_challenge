package com.wei.challenge.cartrack

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wei.challenge.cartrack.network.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class DetailActivity : AppCompatActivity() {

    private val usersApi by lazy {
        ApiClient.create()
    }
    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        getUsers()

    }

    private fun getUsers() {
        disposable = usersApi.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    Timber.d(result.toString())
                    //setupRecycler(result)
                },
                { error -> Timber.e("ERROR:"+ error.message) }
            )

    }

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, DetailActivity::class.java)
            context.startActivity(intent)
        }
    }
}
