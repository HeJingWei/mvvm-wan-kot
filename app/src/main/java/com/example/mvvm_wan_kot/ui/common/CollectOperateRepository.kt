package com.example.mvvm_wan_kot.ui.common

import com.example.mvvm_wan_kot.common.base.BaseRepository
import com.example.mvvm_wan_kot.common.network.RetrofitClient

class CollectOperateRepository : BaseRepository() {
    suspend fun collectCancel(id:Int) = RetrofitClient.service.collectCancel(id).apiData()
    suspend fun collect(id:Int) = RetrofitClient.service.collect(id).apiData()
}