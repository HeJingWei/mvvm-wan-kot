package com.example.mvvm_wan_kot.ui.main.mine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.App
import com.example.mvvm_wan_kot.common.base.BaseViewModel

class MineViewModel constructor(val mineRepository: MineRepository) : BaseViewModel() {

    private val _uiState = MutableLiveData<MineUiModel>()
    val uiState: LiveData<MineUiModel>
        get() = _uiState

    fun idIsVisible(){
        if (mineRepository.isLogin()){
            val userInfo = mineRepository.getUserInfo()
            emitMineUiModel(true,userInfo?.username,userInfo?.id)
        }else{
            emitMineUiModel(false,userName = App.Companion.instance.getString(R.string.home_mine_login))
        }
    }

    private fun emitMineUiModel(isLogin: Boolean = false, userName: String? = null, userId: Int? = 0) {
        val mineUiModel = MineUiModel(isLogin, userName,userId.toString())
        _uiState.value = mineUiModel
    }

    data class MineUiModel(
        val isLogin: Boolean,
        var userName: String?,
        var userId: String?
    )
}