package com.example.mvvm_wan_kot.ui.main.home.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.App
import com.example.mvvm_wan_kot.common.base.BaseViewModel
import com.example.mvvm_wan_kot.model.bean.Article
import com.example.mvvm_wan_kot.model.bean.Banner
import com.example.mvvm_wan_kot.ui.common.CollectOperateRepository

class PopularViewModel constructor(val popularRepository: PopularRepository,val collectOperateRepository: CollectOperateRepository) : BaseViewModel() {
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

    fun collect(isCollect : Boolean,id:Int){
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
                showDialogLoading = false
            )
        })
    }

    private fun emitArticleUiState(
        showDialogLoading: Boolean = false,
        collectToast: String? = null,
        showLoading: Boolean = false,
        article: List<Article>? = null,
        banner: List<Banner>? = null,
        isRefresh: Boolean = false,
        needLogin: Boolean? = null
    ) {
        val uiModel =
            ArticleUiModel(showDialogLoading,collectToast,showLoading, article, banner, isRefresh, needLogin)
        _uiState.value = uiModel
    }

    data class ArticleUiModel(
        val showDialogLoading:Boolean,
        var collectToast: String?,
        val showLoading: Boolean,
        var article: List<Article>?,
        var banner: List<Banner>?,
        val isRefresh: Boolean, // 刷新
        val needLogin: Boolean? = null
    )
}