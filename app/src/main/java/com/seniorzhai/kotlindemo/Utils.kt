package com.seniorzhai.kotlindemo

import android.app.Activity
import android.content.Context
import android.support.design.widget.Snackbar
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

/**
 * Created by zhai on 16/3/21.
 */
fun Context.toast(message: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, duration).show()
}

fun View.snakerbar(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, message, duration);
}

fun Context.getVersionName(): String {
    return this.packageManager.getPackageInfo(this.packageName, 0).versionName
}

fun Context.dipToPx(dip: Float): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, this.resources.displayMetrics).toInt();
}

fun Context.spToPx(sp: Float): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, this.resources.displayMetrics).toInt()
}

fun Context.showKeyBoard(view: View) {
    val imm: InputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager;
    imm.showSoftInput(view, InputMethodManager.RESULT_SHOWN);
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
}

fun Context.hideKeyBoard(view: View) {
    val imm: InputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Activity.getScreenWidth(): Int {
    val displayerMetrics: DisplayMetrics = DisplayMetrics()
    this.windowManager.defaultDisplay.getMetrics(displayerMetrics)
    return displayerMetrics.widthPixels
}

fun Activity.getScreenHeight(): Int {
    val displayerMetrics: DisplayMetrics = DisplayMetrics()
    this.windowManager.defaultDisplay.getMetrics(displayerMetrics)
    return displayerMetrics.heightPixels
}

