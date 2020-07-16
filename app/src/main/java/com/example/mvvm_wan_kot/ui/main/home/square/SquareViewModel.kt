package com.example.mvvm_wan_kot.ui.main.home.square

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_wan_kot.common.base.BaseViewModel
import com.example.mvvm_wan_kot.model.bean.Article

class SquareViewModel constructor(val squareRepository: SquareRepository) : BaseViewModel() {
    private var currPage = 0

    private val _uiState = MutableLiveData<SquareUiModel>()
    val uiState: LiveData<SquareUiModel>
        get() = _uiState

    val onRefresh: () -> Unit = {
        getSquareList(true)
    }

    fun getSquareList(isRefresh: Boolean) {
        if (isRefresh) currPage = 0
        launchOnUI(block = {
            val squareList = squareRepository.getSquareList(currPage)
            if (squareList.offset >= squareList.total) {
                emitArticleUiState(showLoading = false, showEnd = true)
                return@launchOnUI
            }
            emitArticleUiState(isRefresh = isRefresh,showLoading = false,article = squareList.datas)

        }, error = {
            emitArticleUiState(showLoading = false)
        })
    }

   private fun emitArticleUiState(
         showLoading: Boolean = false,
         article: List<Article>? = null,
         isRefresh: Boolean = false,
         needLogin: Boolean? = null,
         showEnd: Boolean = false
    ) {
        val uiModel = SquareUiModel(showLoading, article, isRefresh, needLogin,showEnd)
        _uiState.value = uiModel
    }

    data class SquareUiModel(
        val showLoading: Boolean,
        var article: List<Article>?,
        val isRefresh: Boolean, // 刷新
        val needLogin: Boolean? = null,
        val showEnd: Boolean // 加载更多
    )
}