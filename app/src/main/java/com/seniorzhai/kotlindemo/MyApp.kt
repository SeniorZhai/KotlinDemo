package com.seniorzhai.kotlindemo

import android.app.Application

/**
 * Created by zhai on 16/3/15.
 */
class MyApp : Application() {
    companion object {
        private var instance: MyApp? = null
        fun instance() = instance!!
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}