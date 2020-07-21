package com.example.mvvm_wan_kot.ui.collect

import com.example.mvvm_wan_kot.common.base.BaseRepository
import com.example.mvvm_wan_kot.common.network.RetrofitClient

class CollectRepository : BaseRepository() {
    suspend fun getCollect(page:Int) = RetrofitClient.service.getCollect(page).apiData()
}