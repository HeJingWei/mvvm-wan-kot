package com.example.mvvm_wan_kot.ui.main.home.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_wan_kot.common.base.BaseViewModel
import com.example.mvvm_wan_kot.model.bean.Article
import com.example.mvvm_wan_kot.model.bean.Banner

class PopularViewModel constructor(val popularRepository: PopularRepository) : BaseViewModel() {
    private val _uiState = MutableLiveData<ArticleUiModel>()
    val uiState: LiveData<ArticleUiModel>
        get() = _uiState

    val onRefresh: () -> Unit = {
        refreshPopularList(true)
    }

    fun refreshPopularList(isRefresh: Boolean) {
        launchOnUI(block = {
            val topArticleList = popularRepository.getTopArticleList()
            val bannerList = popularRepository.getBanner()
            emitArticleUiState(
                showLoading = false,
                article = topArticleList,
                banner = bannerList,
                isRefresh = isRefresh
            )
        }, error = {
            emitArticleUiState(showLoading = false)
        })
    }

    private fun emitArticleUiState(
        showLoading: Boolean = false,
        article: List<Article>? = null,
        banner: List<Banner>? = null,
        isRefresh: Boolean = false,
        needLogin: Boolean? = null
    ) {
        val uiModel =
            ArticleUiModel(showLoading, article, banner, isRefresh, needLogin)
        _uiState.value = uiModel
    }

    data class ArticleUiModel(
        val showLoading: Boolean,
        var article: List<Article>?,
        var banner: List<Banner>?,
        val isRefresh: Boolean, // 刷新
        val needLogin: Boolean? = null
    )
}