package com.seniorzhai.kotlindemo.clazz

import android.content.Context
import android.widget.Toast

/**
 * Created by zhai on 16/3/4.
 */
// 默认所有类都是final 必须open/abstract的类才能继承
open class class1 {
    // 会有默认的构造方法

    fun add(a: Int, b: Int): Int {
        return a + b
    }

    fun subtract(a: Int, b: Int): Int = a - b

    // 默认参数，调用时可缺省
    fun toast(context: Context, text: String, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, text, length)
    }

    // 自定义setter getter
    var name: String = ""
        get() = field.toUpperCase()
        set(value) {
            field = "Name$value"
        }

}

class SupClass(name: String) : class1() {

}