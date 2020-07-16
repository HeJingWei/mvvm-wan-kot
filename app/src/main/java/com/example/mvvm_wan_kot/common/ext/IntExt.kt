package com.example.mvvm_wan_kot.common.ext

import com.example.mvvm_wan_kot.common.App
import com.example.mvvm_wan_kot.common.utils.dpToPx
import com.example.mvvm_wan_kot.common.utils.pxToDp


fun Int.toPx() = dpToPx(App.instance, this.toFloat())

fun Int.toIntPx() = dpToPx(App.instance, this.toFloat()).toInt()

fun Int.toDp() = pxToDp(App.instance, this.toFloat())
fun Int.toIntDp() = pxToDp(App.instance, this.toFloat()).toInt()