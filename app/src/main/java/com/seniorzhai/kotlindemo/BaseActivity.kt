package com.seniorzhai.kotlindemo

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

/**
 * Created by zhai on 16/3/4.
 */
open class BaseActivity : AppCompatActivity() {
    fun Context.toast(text: String, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, text, length).show()
    }
}