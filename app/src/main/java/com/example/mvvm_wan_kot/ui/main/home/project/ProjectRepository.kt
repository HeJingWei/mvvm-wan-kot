package com.example.mvvm_wan_kot.ui.main.home.project

import com.example.mvvm_wan_kot.common.base.BaseRepository
import com.example.mvvm_wan_kot.common.network.RetrofitClient

class ProjectRepository : BaseRepository() {
    suspend fun getProjectTree() = RetrofitClient.service.getProjectTree().apiData()

    suspend fun getProjectList(page: Int, cid: Int) =
        RetrofitClient.service.getProjectByAuthor(page, cid).apiData()
}