package com.wei.challenge.cartrack

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, DetailActivity::class.java)
            context.startActivity(intent)
        }
    }
}
