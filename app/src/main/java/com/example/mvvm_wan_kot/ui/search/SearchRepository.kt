package com.example.mvvm_wan_kot.ui.search

import com.example.mvvm_wan_kot.common.base.BaseRepository
import com.example.mvvm_wan_kot.common.network.RetrofitClient
import com.example.mvvm_wan_kot.model.bean.HotKey
import com.example.mvvm_wan_kot.model.room.RoomHelper

class SearchRepository : BaseRepository() {
    suspend fun getHotKey() = RetrofitClient.service.getHotKey().apiData()
    suspend fun queryArticleList(page: Int, key: String) =
        RetrofitClient.service.queryArticleListByKey(page, key).apiData()

    suspend fun getSearchHistory() = RoomHelper.searchHistoryDao.query()
    suspend fun deleteSearchHistory(hotKey: HotKey) = RoomHelper.searchHistoryDao.delete(hotKey)
    suspend fun insertSearchHistory(hotKey: HotKey) = RoomHelper.searchHistoryDao.insert(hotKey)
}