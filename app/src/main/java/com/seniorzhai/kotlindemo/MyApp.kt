package com.seniorzhai.kotlindemo

import android.app.Application
import android.database.sqlite.SQLiteOpenHelper
import com.seniorzhai.kotlindemo.Data.MyDataBaseHelper

/**
 * Created by zhai on 16/3/15.
 */
class MyApp : Application() {
    // 使用lazy委托只有在第一次调用的时候才会初始化
    val database: SQLiteOpenHelper by lazy {
        MyDataBaseHelper(instance())
    }

    companion object {
        private var instance: MyApp? = null
        fun instance() = instance!!
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}