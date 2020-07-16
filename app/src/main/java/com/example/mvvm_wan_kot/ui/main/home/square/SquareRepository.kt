package com.example.mvvm_wan_kot.ui.main.home.square

import com.example.mvvm_wan_kot.common.base.BaseRepository
import com.example.mvvm_wan_kot.common.network.RetrofitClient

class SquareRepository : BaseRepository() {
    suspend fun getSquareList(page:Int) = RetrofitClient.service.getUserArticleList(page).apiData()
}