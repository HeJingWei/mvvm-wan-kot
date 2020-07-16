package com.example.mvvm_wan_kot.common.ext

import com.example.mvvm_wan_kot.common.App
import com.example.mvvm_wan_kot.common.utils.dpToPx
import com.example.mvvm_wan_kot.common.utils.pxToDp

/**
 * float拓展函数
 * */
fun Float.toPx() = dpToPx(App.instance, this)

fun Float.toIntPx() = dpToPx(App.instance, this).toInt()

fun Float.toDp() = pxToDp(App.instance, this)

fun Float.toIntDp() = pxToDp(App.instance, this).toInt()