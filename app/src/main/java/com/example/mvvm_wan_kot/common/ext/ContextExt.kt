package com.example.mvvm_wan_kot.common.ext

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import com.dylanc.loadinghelper.LoadingHelper
import com.dylanc.loadinghelper.ViewType
import com.example.mvvm_wan_kot.common.adapter.NavIconType
import com.example.mvvm_wan_kot.common.adapter.TitleAdapter

/**
 * 实现将特定文本复制到剪贴板的功能。
 * @param[label] User-visible label for the clip data.
 * @param[text] The actual text in the clip.
 */
fun Context.copyTextIntoClipboard(text: CharSequence?, label: String? = "") {
    if (text.isNullOrEmpty()) return
    val cbs = applicationContext.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
        ?: return
    cbs.setPrimaryClip(ClipData.newPlainText(label, text))
}

fun Context.showToast(message: CharSequence) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showToast(@StringRes message: Int) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Activity.setToolbar(title: String, type: NavIconType = NavIconType.NONE) =
    LoadingHelper(this).apply {
        register(ViewType.TITLE, TitleAdapter(title, type))
        setDecorHeader(ViewType.TITLE)
    }