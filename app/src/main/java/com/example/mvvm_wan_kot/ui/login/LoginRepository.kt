package com.example.mvvm_wan_kot.ui.login

import com.example.mvvm_wan_kot.common.base.BaseRepository
import com.example.mvvm_wan_kot.common.network.RetrofitClient

class LoginRepository : BaseRepository() {
    suspend fun login(userName:String,passWord:String) = RetrofitClient.service.login(userName,passWord).apiData()
}