package com.example.mvvm_wan_kot.common.utils

import android.content.Context

fun dpToPx(context: Context, dp: Float): Float {
    return dp * context.resources.displayMetrics.density
}

fun pxToDp(context: Context, px: Float): Float {
    return px / context.resources.displayMetrics.density
}