package com.example.mvvm_wan_kot.ui.login.register

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_wan_kot.common.base.BaseViewModel

class RegisterViewModel(val registerRepository: RegisterRepository) : BaseViewModel() {
    val account = ObservableField<String>("")
    val password = ObservableField<String>("")
    val rePassword = ObservableField<String>("")

    private val _uiState = MutableLiveData<RegisterUiModel>()
    val uiState: LiveData<RegisterUiModel>
        get() = _uiState

    val verifyInput: (String) -> Unit = {
        emitRegisterUiModel(
            btnEnabled = isInputValid(
                account.get()
                    ?: "", password.get() ?: "", rePassword.get() ?: ""
            )
        )
    }

    fun register() {
        emitRegisterUiModel(isShowing = true)
        launchOnUI(block = {
            if (account.get().isNullOrBlank() || password.get().isNullOrBlank() || rePassword.get()
                    .isNullOrBlank()
            ) {
                emitRegisterUiModel(btnEnabled = false, isShowing = false)
                return@launchOnUI
            }
            val res = registerRepository.register(
                account.get() ?: "",
                password.get() ?: "",
                rePassword.get() ?: ""
            )
            emitRegisterUiModel(isShowing = false, isSuccess = true)
        }, error = {
            emitRegisterUiModel(isShowing = false, isSuccess = false)
        })
    }


    private fun emitRegisterUiModel(
        isShowing: Boolean = false, isSuccess: Boolean = false, btnEnabled: Boolean = false
    ) {
        _uiState.value = RegisterUiModel(isShowing, isSuccess, btnEnabled)
    }

    data class RegisterUiModel(
        val isShowing: Boolean,
        val isSuccess: Boolean,
        val btnEnabled: Boolean
    )
}