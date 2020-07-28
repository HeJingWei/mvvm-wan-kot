package com.example.mvvm_wan_kot.ui.main.system

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.App
import com.example.mvvm_wan_kot.common.base.BaseViewModel
import com.example.mvvm_wan_kot.model.bean.Article
import com.example.mvvm_wan_kot.model.bean.SystemBean
import com.example.mvvm_wan_kot.ui.common.CollectOperateRepository

class SystemViewModel constructor(
    val systemRepository: SystemRepository,
    val collectOperateRepository: CollectOperateRepository
) : BaseViewModel() {

    private val _uiState = MutableLiveData<SysUiModel>()
    val uiState: LiveData<SysUiModel>
        get() = _uiState

    private var currPage = 0
     var cid = 0

    val sysRefresh: () -> Unit = {
        getSysList()
    }

    fun getSysList() {
        launchOnUI(block = {
            val res = systemRepository.getSysTree()
            emitSysUiState(showLoading = false, sysList = res)
        }, error = {
            emitSysUiState(showLoading = false)
        })
    }

    fun collect(isCollect: Boolean, id: Int) {
        emitSysUiState(showDialogLoading = true)
        launchOnUI(block = {
            if (isCollect)
                collectOperateRepository.collectCancel(id)
            else
                collectOperateRepository.collect(id)
            emitSysUiState(
                collectSuccess = App.instance.getString(R.string.operate_success),
                showDialogLoading = false
            )
        }, error = {
            emitSysUiState(showDialogLoading = false)
        })
    }

    val sysListRefresh:()->Unit={
        getSysListByCid(true)
    }

    fun getSysListByCid(isRefresh: Boolean){
        launchOnUI(block = {
            if (isRefresh) currPage = 0
            val res = systemRepository.getSysList(currPage,cid)
            if (res.offset >= res.total){
                emitSysUiState(showLoading = false,showEnd = true)
                return@launchOnUI
            }
            emitSysUiState(isRefresh = isRefresh,showLoading = false,articleList = res.datas)
            currPage += 1
        },error = {
            emitSysUiState(showLoading = false)
        })
    }

    private fun emitSysUiState(
        showLoading: Boolean = false,
        sysList: List<SystemBean>? = null,
        collectSuccess: String? = null,
        showDialogLoading: Boolean = false,
        isRefresh: Boolean = false,
        showEnd: Boolean = false,
        articleList: List<Article>? = null
    ) {
        _uiState.value = SysUiModel(showLoading, sysList, collectSuccess, showDialogLoading,isRefresh,showEnd,articleList)
    }

    data class SysUiModel(
        val showLoading: Boolean,
        var sysList: List<SystemBean>?,
        var collectSuccess: String?,
        val showDialogLoading: Boolean,
        val isRefresh:Boolean,
        val showEnd:Boolean,
        var articleList:List<Article>?
    )
}