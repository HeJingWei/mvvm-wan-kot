package com.example.mvvm_wan_kot.ui.main.home.project

import com.example.mvvm_wan_kot.common.base.BaseRepository
import com.example.mvvm_wan_kot.common.network.RetrofitClient

class ProjectRepository : BaseRepository(){
    suspend fun getProjectChapters() = RetrofitClient.service.getProjectChapters().apiData()
    suspend fun getProjectList(id:Int,page:Int) = RetrofitClient.service.getProjectList(id,page).apiData()
}