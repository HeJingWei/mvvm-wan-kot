package com.example.mvvm_wan_kot.ui.main.system

import com.example.mvvm_wan_kot.common.base.BaseRepository
import com.example.mvvm_wan_kot.common.network.RetrofitClient

class SystemRepository : BaseRepository() {
    suspend fun getSysTree() = RetrofitClient.service.getSysTree().apiData()
    suspend fun getSysList(page:Int,cid : Int) = RetrofitClient.service.getSystemList(page,cid).apiData()
}