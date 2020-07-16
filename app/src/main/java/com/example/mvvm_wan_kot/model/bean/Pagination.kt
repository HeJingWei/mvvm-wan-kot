package com.example.mvvm_wan_kot.model.bean


data class Pagination<T>(
    val offset: Int,
    val size: Int,
    val total: Int,
    val pageCount: Int,
    val curPage: Int,
    val over: Boolean,
    val datas: List<T>
)