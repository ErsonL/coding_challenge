package com.wei.challenge.cartrack

import android.app.Application
import com.facebook.stetho.Stetho

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appInternal = this
        Stetho.initializeWithDefaults(this);
    }



    companion object {
        var appInternal : App? = null
        val app : App
            get() = appInternal!!


    }
}


val app = App.app