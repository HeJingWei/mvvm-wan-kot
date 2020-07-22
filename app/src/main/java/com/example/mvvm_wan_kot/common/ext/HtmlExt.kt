package com.example.mvvm_wan_kot.common.ext

import androidx.core.text.HtmlCompat

fun String?.htmlToSpanned() =
    if (this.isNullOrEmpty()) "" else HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)