package com.example.mvvm_wan_kot.ui.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_wan_kot.common.base.BaseViewModel

class SettingViewModel(val settingRepository: SettingRepository) : BaseViewModel() {

    private val _uiState = MutableLiveData<SettingUiModel>()
    val uiState: LiveData<SettingUiModel>
        get() = uiState

    fun isExitLogin() {
        emitSettingUiModel(isExit = settingRepository.isLogin())
    }

    fun loginOut() {
        settingRepository.clearLoginState()
        emitSettingUiModel(loginOutSuccess = true)
    }

    private fun emitSettingUiModel(isExit: Boolean = false,loginOutSuccess: Boolean = false) {
        _uiState.value = SettingUiModel(isExit,loginOutSuccess)
    }

    data class SettingUiModel(
        val isExit: Boolean,
        val loginOutSuccess: Boolean
    )
}