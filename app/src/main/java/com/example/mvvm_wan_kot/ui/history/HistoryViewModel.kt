package com.example.mvvm_wan_kot.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_wan_kot.common.base.BaseViewModel
import com.example.mvvm_wan_kot.model.bean.Article

class HistoryViewModel(private val historyRepository: HistoryRepository) : BaseViewModel() {

    private val _uiState = MutableLiveData<HistoryUiModel>()
    val uiState : LiveData<HistoryUiModel>
    get() = _uiState

    val refreshHistory : () -> Unit = {
        getAllHistory(true)
    }

    fun getAllHistory(isRefresh:Boolean){
        launchOnUI(block = {
            val res = historyRepository.queryAllHistory()
            emitHistoryUiState(isRefresh = isRefresh,historyList = res,showLoading = false)
        },error = {
            emitHistoryUiState(showLoading = false)
        })
    }

    fun deleteHistory(vararg articles: Article){
        emitHistoryUiState(showDialogLoading = true)
        launchOnUI(block = {
            historyRepository.deleteHistory(*articles)
            emitHistoryUiState(deleteToast = "删除成功",showDialogLoading = false)
        },error = {
            emitHistoryUiState(deleteToast = it.message,showDialogLoading = false)
        })
    }

    private fun emitHistoryUiState(
        isRefresh: Boolean = false,
        showEnd: Boolean = false,
        historyList: List<Article>? = null,
        showLoading: Boolean = false,
        showDialogLoading: Boolean = false,
        deleteToast: String? = null
    ){
        _uiState.value = HistoryUiModel(isRefresh,showEnd,historyList,showLoading,showDialogLoading,deleteToast)
    }

    data class HistoryUiModel(
        val isRefresh:Boolean,
        val showEnd:Boolean,
        var historyList:List<Article>?,
        val showLoading:Boolean,
        val showDialogLoading:Boolean,
        var deleteToast:String?
    )
}