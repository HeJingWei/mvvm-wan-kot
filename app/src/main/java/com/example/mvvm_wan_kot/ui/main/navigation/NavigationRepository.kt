package com.example.mvvm_wan_kot.ui.main.navigation

import com.example.mvvm_wan_kot.common.base.BaseRepository
import com.example.mvvm_wan_kot.common.network.RetrofitClient

class NavigationRepository : BaseRepository() {
    suspend fun getNavigation() = RetrofitClient.service.getNavigation().apiData()
}