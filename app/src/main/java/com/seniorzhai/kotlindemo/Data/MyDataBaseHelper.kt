package com.seniorzhai.kotlindemo.Data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by zhai on 16/3/21.
 */
class MyDataBaseHelper(context: Context, name: String? = "Kotlin_DB", factory: SQLiteDatabase.CursorFactory? = null, version: Int = 1) : SQLiteOpenHelper(context, name, factory, version) {
    companion object {
        val create = "create table book (" + "id integer primary key autoincrement," + "author text, " + "price real, " + "pages integer, " + "name text)";
    }

    var context: Context? = null

    init {
        this.context = context;
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(create)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + "book")
        onCreate(db)
    }

}