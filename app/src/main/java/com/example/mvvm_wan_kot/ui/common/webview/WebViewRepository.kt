package com.example.mvvm_wan_kot.ui.common.webview

import com.example.mvvm_wan_kot.common.base.BaseRepository
import com.example.mvvm_wan_kot.model.bean.Article
import com.example.mvvm_wan_kot.model.room.RoomHelper

class WebViewRepository : BaseRepository() {
    suspend fun addReadHistory(article: Article) = RoomHelper.readHistoryDao.insert(article)
}