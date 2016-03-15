package com.seniorzhai.kotlindemo

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

/**
 * Created by zhai on 16/3/4.
 */
open class BaseActivity : AppCompatActivity() {
    fun Context.toast(text: String, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, text, length).show()
    }

    fun Context.toast(text: Int, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, text, length).show()
    }

    // 内联函数，会在编译的时候替换掉
    inline fun supportsLollipop(code: () -> Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            code()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportsLollipop {
            window.statusBarColor = Color.BLUE
        }
    }
}