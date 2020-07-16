package com.example.mvvm_wan_kot.ui.main.home.popular

import com.example.mvvm_wan_kot.common.base.BaseRepository
import com.example.mvvm_wan_kot.common.network.RetrofitClient

class PopularRepository : BaseRepository(){
        suspend fun getTopArticleList() = RetrofitClient.service.getTopArticleList().apiData()

        suspend fun getBanner() = RetrofitClient.service.getBanner().apiData()
}