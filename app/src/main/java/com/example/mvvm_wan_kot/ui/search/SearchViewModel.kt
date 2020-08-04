package com.example.mvvm_wan_kot.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_wan_kot.common.base.BaseViewModel
import com.example.mvvm_wan_kot.model.bean.Article
import com.example.mvvm_wan_kot.model.bean.HotKey

class SearchViewModel(private val searchRepository: SearchRepository) : BaseViewModel() {

    private val _uiState = MutableLiveData<SearchUiModel>()
    val uiState: LiveData<SearchUiModel>
        get() = _uiState

    var currPage = 0
    var key = ""

    fun getSearchHistory(){
        launchOnUI(block = {
            val res = searchRepository.getSearchHistory()
            emitSearchUiState(historyList = res)
        })
    }

    fun addSearchHistory(hotKey: HotKey){
        launchOnUI(block = {
            val res = searchRepository.getSearchHistory()
            res.forEach {
                if (it.name == hotKey.name) searchRepository.deleteSearchHistory(it)
            }
            searchRepository.insertSearchHistory(hotKey)
            getSearchHistory()
        })
    }

    fun deleteSearchHistory(hotKey: HotKey){
        launchOnUI(block = {
            searchRepository.deleteSearchHistory(hotKey)
            getSearchHistory()
        })
    }

    fun getHotKey() {
        launchOnUI(
            block = {
                val res = searchRepository.getHotKey()
                emitSearchUiState(hotKeyList = res)
            }
        )
    }

    fun queryArticleList() {
        emitSearchUiState(showDialogLoading = true)
        launchOnUI(block = {
            val res = searchRepository.queryArticleList(currPage, key)
            if (res.offset >= res.total) {
                emitSearchUiState(showEnd = true, showDialogLoading = false)
                return@launchOnUI
            }
            var data = ArrayList<Article>()
            data.addAll(res.datas)
            emitSearchUiState(articleList = data, showDialogLoading = false)
            currPage += 1
        }, error = {
            emitSearchUiState(showDialogLoading = false)
        })
    }

    private fun emitSearchUiState(
        showEnd: Boolean = false,
        historyList: List<HotKey>? = null,
        hotKeyList: List<HotKey>? = null,
        articleList: List<Article>? = null,
        showDialogLoading: Boolean = false
    ) {
        _uiState.value =
            SearchUiModel(showEnd, historyList, hotKeyList, articleList, showDialogLoading)
    }

    data class SearchUiModel(
        val showEnd: Boolean,
        var historyList: List<HotKey>?,
        var hotKeyList: List<HotKey>?,
        var articleList: List<Article>?,
        val showDialogLoading: Boolean
    )
}