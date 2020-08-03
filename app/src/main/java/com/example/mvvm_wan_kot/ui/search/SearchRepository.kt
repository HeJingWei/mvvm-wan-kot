package com.example.mvvm_wan_kot.ui.search

import com.example.mvvm_wan_kot.common.base.BaseRepository
import com.example.mvvm_wan_kot.common.network.RetrofitClient

class SearchRepository : BaseRepository() {
    suspend fun getHotKey() = RetrofitClient.service.getHotKey().apiData()
    suspend fun queryArticleList(page: Int, key: String) =
        RetrofitClient.service.queryArticleListByKey(page, key).apiData()
}