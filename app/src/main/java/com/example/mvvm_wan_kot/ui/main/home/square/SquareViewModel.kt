package com.example.mvvm_wan_kot.ui.main.home.square

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.App
import com.example.mvvm_wan_kot.common.base.BaseViewModel
import com.example.mvvm_wan_kot.model.bean.Article
import com.example.mvvm_wan_kot.ui.common.CollectOperateRepository

class SquareViewModel constructor(
    val squareRepository: SquareRepository,
    val collectOperateRepository: CollectOperateRepository
) : BaseViewModel() {
    private var currPage = 0

    private val _uiState = MutableLiveData<SquareUiModel>()
    val uiState: LiveData<SquareUiModel>
        get() = _uiState

    val onRefresh: () -> Unit = {
        getSquareList(true)
    }

    fun collect(isCollect: Boolean, id: Int) {
        emitArticleUiState(showDialogLoading = true)
        launchOnUI(block = {
            if (!isCollect) collectOperateRepository.collect(id) else collectOperateRepository.collectCancel(
                id
            )
            emitArticleUiState(
                showDialogLoading = false,
                collectToast = App.instance.getString(R.string.operate_success)
            )
        }, error = {
            emitArticleUiState(
                showDialogLoading = false,
                collectToast = App.instance.getString(R.string.operate_fail)
            )
        })
    }

    fun getSquareList(isRefresh: Boolean) {
        if (isRefresh) currPage = 0
        launchOnUI(block = {
            val squareList = squareRepository.getSquareList(currPage)
            if (squareList.offset >= squareList.total) {
                emitArticleUiState(showLoading = false, showEnd = true)
                return@launchOnUI
            }
            emitArticleUiState(
                isRefresh = isRefresh,
                showLoading = false,
                article = squareList.datas
            )
            currPage += 1
        }, error = {
            emitArticleUiState(showLoading = false)
        })
    }

    private fun emitArticleUiState(
        collectToast: String? = null,
        showDialogLoading: Boolean = false,
        showLoading: Boolean = false,
        article: List<Article>? = null,
        isRefresh: Boolean = false,
        needLogin: Boolean? = null,
        showEnd: Boolean = false
    ) {
        val uiModel = SquareUiModel(
            collectToast,
            showDialogLoading,
            showLoading,
            article,
            isRefresh,
            needLogin,
            showEnd
        )
        _uiState.value = uiModel
    }

    data class SquareUiModel(
        var collectToast: String?,
        val showDialogLoading: Boolean,
        val showLoading: Boolean,
        var article: List<Article>?,
        val isRefresh: Boolean, // 刷新
        val needLogin: Boolean? = null,
        val showEnd: Boolean // 加载更多
    )
}