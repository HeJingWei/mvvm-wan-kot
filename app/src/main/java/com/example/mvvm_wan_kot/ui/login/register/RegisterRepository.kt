package com.example.mvvm_wan_kot.ui.login.register

import com.example.mvvm_wan_kot.common.base.BaseRepository
import com.example.mvvm_wan_kot.common.network.RetrofitClient

class RegisterRepository : BaseRepository() {
    suspend fun register(account:String,password:String,rePassword:String) = RetrofitClient.service.register(account,password,rePassword).apiData()
}