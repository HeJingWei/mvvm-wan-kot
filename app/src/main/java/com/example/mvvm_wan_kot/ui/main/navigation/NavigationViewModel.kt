package com.example.mvvm_wan_kot.ui.main.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_wan_kot.common.base.BaseViewModel
import com.example.mvvm_wan_kot.model.bean.Navigation

class NavigationViewModel constructor(val navigationRepository: NavigationRepository) :
    BaseViewModel() {

    private val _uiState = MutableLiveData<NavigationUiModel>()
    val uiState: LiveData<NavigationUiModel>
        get() = _uiState

    val refreshNavigation: () -> Unit = {
        getNavigationList(true)
    }

    fun getNavigationList(isRefresh: Boolean) {
        launchOnUI(block = {
            val res = navigationRepository.getNavigation()
            emitNavigationUiState(showLoading = false, isRefresh = isRefresh, navigationList = res)
        }, error = {
            emitNavigationUiState(showLoading = false)
        })
    }

    private fun emitNavigationUiState(
        isRefresh: Boolean = false,
        showLoading: Boolean = false,
        navigationList: List<Navigation>? = null
    ) {
        _uiState.value = NavigationUiModel(isRefresh, showLoading, navigationList)
    }

    data class NavigationUiModel(
        val isRefresh: Boolean,
        val showLoading: Boolean,
        var navigationList: List<Navigation>?
    )
}