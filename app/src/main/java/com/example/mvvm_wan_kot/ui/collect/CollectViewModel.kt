package com.example.mvvm_wan_kot.ui.collect

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.App
import com.example.mvvm_wan_kot.common.base.BaseViewModel
import com.example.mvvm_wan_kot.model.bean.Article
import com.example.mvvm_wan_kot.ui.common.CollectOperateRepository

class CollectViewModel(
    val collectRepository: CollectRepository,
    val collectCancelRepository: CollectOperateRepository
) : BaseViewModel() {
    private val _uiState = MutableLiveData<CollectUiModel>()
    val uiState: LiveData<CollectUiModel>
        get() = _uiState

    private var currPage = 0

    val onRefresh: () -> Unit = {
        getCollectList(true)
    }

    fun collectCancel(id: Int) {
        emitCollectUiState(showLoadingDialog = true)
        launchOnUI(block = {
            collectCancelRepository.collectCancel(id)
            emitCollectUiState(cancelSuccess = App.instance.getString(R.string.operate_success),showLoadingDialog = false)
        }, error = {
            emitCollectUiState(cancelSuccess = App.instance.getString(R.string.operate_fail),showLoadingDialog = false)
        }
        )
    }

    fun getCollectList(isRefresh: Boolean) {
        launchOnUI(block = {
            if (isRefresh) currPage = 0
            val res = collectRepository.getCollect(currPage)
            if (res.offset >= res.total) {
                emitCollectUiState(showLoading = false, showEnd = true)
                return@launchOnUI
            }
            emitCollectUiState(isRefresh = isRefresh,showEnd = false, collectList = res.datas)
            currPage += 1
        }, error = {
            emitCollectUiState(showLoading = false)
        })
    }


    private fun emitCollectUiState(
        cancelSuccess: String? = null,
        isRefresh: Boolean = false,
        showLoadingDialog: Boolean = false,
        showLoading: Boolean = false,
        showEnd: Boolean = false,
        collectList: MutableList<Article>? = null
    ) {
        _uiState.value = CollectUiModel(cancelSuccess,isRefresh,showLoadingDialog, showLoading, showEnd, collectList)
    }

    data class CollectUiModel(
        val cancelSuccess:String?,
        val isRefresh: Boolean,
        val showLoadingDialog: Boolean,
        val showLoading: Boolean,
        val showEnd: Boolean,
        var collectList: MutableList<Article>?
    )
}