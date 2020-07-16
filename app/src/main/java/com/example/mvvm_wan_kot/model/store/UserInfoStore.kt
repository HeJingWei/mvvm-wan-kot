package com.example.mvvm_wan_kot.model.store

import com.example.mvvm_wan_kot.common.App
import com.example.mvvm_wan_kot.common.utils.clearSpValue
import com.example.mvvm_wan_kot.common.utils.getSpValue
import com.example.mvvm_wan_kot.common.utils.putSpValue
import com.example.mvvm_wan_kot.model.bean.User
import com.google.gson.Gson

object UserInfoStore {
    private const val SP_USER_INFO = "sp_user_info"
    private const val KEY_USER_INFO = "userInfo"
    private val mGson by lazy { Gson() }

    fun isLogin(): Boolean {
        val userInfoStr = getSpValue(SP_USER_INFO, App.instance, KEY_USER_INFO, "")
        return userInfoStr.isNotEmpty()
    }

    fun getUserInfo(): User? {
        val userInfoStr = getSpValue(SP_USER_INFO, App.instance, KEY_USER_INFO, "")
        return if (userInfoStr.isNotEmpty()) {
            mGson.fromJson(userInfoStr, User::class.java)
        } else {
            null
        }
    }

    fun setUserInfo(userInfo: User) =
        putSpValue(SP_USER_INFO, App.instance, KEY_USER_INFO, mGson.toJson(userInfo))

    fun clearUserInfo() {
        clearSpValue(SP_USER_INFO, App.instance)
    }
}