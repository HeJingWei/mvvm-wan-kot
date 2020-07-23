package com.example.mvvm_wan_kot.ui.main.home.article

import com.example.mvvm_wan_kot.common.base.BaseRepository
import com.example.mvvm_wan_kot.common.network.RetrofitClient

class ArticleRepository : BaseRepository() {
    suspend fun getHomeArticleList(page:Int) = RetrofitClient.service.getHomeArticle(page).apiData()
}