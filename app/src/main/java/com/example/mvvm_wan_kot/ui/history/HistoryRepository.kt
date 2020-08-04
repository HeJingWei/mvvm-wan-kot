package com.example.mvvm_wan_kot.ui.history

import com.example.mvvm_wan_kot.common.base.BaseRepository
import com.example.mvvm_wan_kot.model.bean.Article
import com.example.mvvm_wan_kot.model.room.RoomHelper

class HistoryRepository : BaseRepository() {
    suspend fun queryAllHistory(): List<Article> {
        return RoomHelper.readHistoryDao.getAllHistory()
    }

    suspend fun addReadHistory(article: Article) {
        RoomHelper.readHistoryDao.insert(article)
    }

    suspend fun deleteHistory(vararg articles: Article) {
        RoomHelper.readHistoryDao.delete(*articles)
    }
}