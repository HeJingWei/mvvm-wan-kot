package com.example.mvvm_wan_kot.common.base

import com.example.mvvm_wan_kot.common.network.RetrofitClient
import com.example.mvvm_wan_kot.model.bean.User
import com.example.mvvm_wan_kot.model.store.UserInfoStore

open class BaseRepository {
    fun updateUserInfo(userInfo: User) = UserInfoStore.setUserInfo(userInfo)

    fun isLogin() = UserInfoStore.isLogin()

    fun getUserInfo() = UserInfoStore.getUserInfo()

    fun clearLoginState() {
        UserInfoStore.clearUserInfo()
        RetrofitClient.clearCookie()
    }
}