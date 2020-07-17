package com.example.mvvm_wan_kot.ui.integral

import com.example.mvvm_wan_kot.common.base.BaseRepository
import com.example.mvvm_wan_kot.common.network.RetrofitClient

class IntegralRepository : BaseRepository() {
    suspend fun getUserIntegral() = RetrofitClient.service.getUserIntegral().apiData()
    suspend fun getIntegralList(page:Int) = RetrofitClient.service.getIntegralList(page).apiData()
    suspend fun getIntegralRankList(page:Int) = RetrofitClient.service.getIntegralRankList(page).apiData()
}