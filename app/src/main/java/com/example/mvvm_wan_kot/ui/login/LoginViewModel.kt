package com.example.mvvm_wan_kot.ui.login

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_wan_kot.common.base.BaseViewModel
import com.example.mvvm_wan_kot.model.bean.User

class LoginViewModel(val loginRepository: LoginRepository) : BaseViewModel() {

    val userName = ObservableField<String>("")
    val passWord = ObservableField<String>("")

    private val _uiState = MutableLiveData<LoginUiModel>()
    val uiState: LiveData<LoginUiModel>
        get() = _uiState

    fun login() {
        emitLoginUiState(isShowLoading = true)
        launchOnUI(block = {
            if (userName.get().isNullOrBlank() || passWord.get().isNullOrBlank()) {
                emitLoginUiState(enableLoginButton = false,isShowLoading = false)
                return@launchOnUI
            }
            val user = loginRepository.login(userName.get()
                ?: "", passWord.get() ?: "")
            loginRepository.updateUserInfo(user)
            emitLoginUiState(isShowLoading =  false,isSuccess = true)
        },error = {
            emitLoginUiState(isShowLoading = false)
        })
    }

    val verifyInput: (String) -> Unit = { loginDataChanged() }

    fun loginDataChanged() {
        emitLoginUiState(
            enableLoginButton = isInputValid(
                userName.get()
                    ?: "", passWord.get() ?: ""
            )
        )
    }

    private fun emitLoginUiState(
        isShowLoading: Boolean = false,
        isSuccess: Boolean = false,
        enableLoginButton: Boolean = false,
        user: User? = null
    ) {
        val uiModel = LoginUiModel(isShowLoading,isSuccess, enableLoginButton, user)
        _uiState.value = uiModel
    }

    data class LoginUiModel(
        val isShowLoading: Boolean,
        val isSuccess: Boolean,
        val enableLoginButton: Boolean,
        var user: User?
    )
}