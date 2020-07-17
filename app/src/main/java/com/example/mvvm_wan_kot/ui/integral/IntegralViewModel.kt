package com.example.mvvm_wan_kot.ui.integral

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_wan_kot.common.base.BaseViewModel
import com.example.mvvm_wan_kot.model.bean.IntegralBean
import com.example.mvvm_wan_kot.model.bean.IntegralItem

class IntegralViewModel(val integralRepository: IntegralRepository) : BaseViewModel() {

    private val _uiState = MutableLiveData<IntegralUiModel>()
    val uiState: LiveData<IntegralUiModel>
        get() = _uiState

    private var currPage = 1
    private var rankPage = 1

    val refreshIntegral: () -> Unit = {
        getIntegralList(true)
        getUserIntegral()
    }

    val refreshIntegralRank: () -> Unit = {
        getIntegralList(true)
    }

    fun getIntegralRankList(isRefresh: Boolean) {
        launchOnUI(block = {
            if (isRefresh) {
                emitIntegralUiState(showLoading = true)
                rankPage = 1
            }
            val result = integralRepository.getIntegralRankList(rankPage)
            if (result.offset >= result.total) {
                emitIntegralUiState(showLoading = false, showRankEnd = true)
                return@launchOnUI
            }
            emitIntegralUiState(
                rankIsRefresh = isRefresh,
                integralRankList = result.datas,
                showLoading = false
            )
            rankPage += 1
        }, error = {
            emitIntegralUiState(rankIsRefresh = isRefresh, showLoading = false)
        })
    }

    fun getIntegralList(isRefresh: Boolean) {
        launchOnUI(block = {
            if (isRefresh) {
                emitIntegralUiState(showLoading = true)
                currPage = 1
            }
            val result = integralRepository.getIntegralList(currPage)
            if (result.offset >= result.total) {
                emitIntegralUiState(showLoading = false, showEnd = true)
                return@launchOnUI
            }
            emitIntegralUiState(
                isRefresh = isRefresh,
                integralList = result.datas,
                showLoading = false
            )
            currPage += 1
        }, error = {
            emitIntegralUiState(showLoading = false, isRefresh = isRefresh)
        })
    }

    fun getUserIntegral() {
        launchOnUI(block = {
            val res = integralRepository.getUserIntegral()
            emitIntegralUiState(userIntegral = res)
        })
    }

    private fun emitIntegralUiState(
        isRefresh: Boolean = false,
        integralList: List<IntegralItem>? = null,
        userIntegral: IntegralBean? = null,
        integralRankList: List<IntegralBean>? = null,
        showEnd: Boolean = false,
        showLoading: Boolean = false,
        showRankEnd: Boolean = false,
        rankIsRefresh: Boolean = false
    ) {
        _uiState.value =
            IntegralUiModel(
                isRefresh,
                integralList,
                userIntegral,
                integralRankList,
                showEnd,
                showLoading,
                showRankEnd,
                rankIsRefresh
            )
    }

    data class IntegralUiModel(
        val isRefresh: Boolean,
        var integralList: List<IntegralItem>?,
        var userIntegral: IntegralBean?,
        var integralRankList: List<IntegralBean>?,
        val showEnd: Boolean,
        val showLoading: Boolean,
        val showRankEnd: Boolean,
        val rankIsRefresh: Boolean
    )
}