package com.example.mvvm_wan_kot.common.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.mvvm_wan_kot.common.ext.htmlToSpanned

@BindingAdapter(value = ["htmlToSpanned"])
fun TextView.htmlToSpanned(title:String){
    text = title.htmlToSpanned()
}