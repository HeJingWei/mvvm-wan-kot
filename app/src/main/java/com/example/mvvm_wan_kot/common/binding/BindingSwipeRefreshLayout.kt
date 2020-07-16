package com.example.mvvm_wan_kot.common.binding

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@BindingAdapter("isRefresh")
fun SwipeRefreshLayout.isRefresh(isRefresh: Boolean) {
    isRefreshing = isRefresh
}

@BindingAdapter("onRefresh")
fun SwipeRefreshLayout.onRefresh(action: () -> Unit) {
    setOnRefreshListener { action() }
}