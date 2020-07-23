package com.example.mvvm_wan_kot.ui.main.home.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.App
import com.example.mvvm_wan_kot.common.base.BaseViewModel
import com.example.mvvm_wan_kot.model.bean.Article
import com.example.mvvm_wan_kot.ui.common.CollectOperateRepository

class ArticleViewModel(
    val articleRepository: ArticleRepository,
    val collectOperateRepository: CollectOperateRepository
) : BaseViewModel() {

    private val _uiState = MutableLiveData<ArticleUiModel>()
    val uiState: LiveData<ArticleUiModel>
        get() = _uiState

    private var currPage = 0

    val refreshData: () -> Unit = {
        getArticleList(true)
    }

    fun getArticleList(isRefresh: Boolean) {
        launchOnUI(
            block = {
                if (isRefresh) currPage = 0
                val res = articleRepository.getHomeArticleList(currPage)
                if (res.offset >= res.total) {
                    emitArticleUiState(showEnd = true, showLoading = false)
                    return@launchOnUI
                }
                emitArticleUiState(
                    showLoading = false,
                    isRefresh = isRefresh,
                    articleList = res.datas
                )
                currPage += 1
            },
            error = {
                emitArticleUiState(showLoading = false)
            }
        )
    }

    fun collect(isCollect: Boolean, id: Int) {
        emitArticleUiState(showDialogLoading = true)
        launchOnUI(block = {
            if (isCollect)
                collectOperateRepository.collectCancel(id)
            else
                collectOperateRepository.collect(id)
            emitArticleUiState(
                collectSuccess = App.instance.getString(R.string.operate_success),
                showDialogLoading = false
            )
        }, error = {
            emitArticleUiState(showDialogLoading = false)

        })
    }

    private fun emitArticleUiState(
        showDialogLoading: Boolean = false,
        showLoading: Boolean = false,
        showEnd: Boolean = false,
        isRefresh: Boolean = false,
        articleList: List<Article>? = null,
        collectSuccess: String? = null
    ) {
        _uiState.value =
            ArticleUiModel(
                showDialogLoading,
                showLoading,
                showEnd,
                isRefresh,
                articleList,
                collectSuccess
            )
    }

    data class ArticleUiModel(
        val showDialogLoading: Boolean,
        val showLoading: Boolean,
        val showEnd: Boolean,
        val isRefresh: Boolean,
        var articleList: List<Article>?,
        var collectSuccess: String?
    )
}