package com.example.mvvm_wan_kot.ui.main.home.project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_wan_kot.common.base.BaseViewModel
import com.example.mvvm_wan_kot.model.bean.Article
import com.example.mvvm_wan_kot.model.bean.ProjectChapter

class ProjectViewModel(val projectRepository: ProjectRepository) : BaseViewModel() {

    private val _uiState = MutableLiveData<ProjectUiModel>()
    val uiState: LiveData<ProjectUiModel>
        get() = _uiState

    private var currPage = 1
    var cid = 0

    val refreshData : () -> Unit = {
        getProjectList(true)
    }

    fun getProjectTree(){
        launchOnUI(
            block = {
                val res = projectRepository.getProjectTree()
                emitProjectUiState(projectTree = res)
            }
        )
    }

    fun getProjectList(isRefresh: Boolean){
        launchOnUI(
            block = {
                if (isRefresh) currPage = 1
                val res = projectRepository.getProjectList(currPage,cid)
                if (res.offset >= res.total){
                    emitProjectUiState(showLoading = false,showEnd = true)
                    return@launchOnUI
                }
                emitProjectUiState(isRefresh = isRefresh,showLoading = false,projectList = res.datas)
                currPage += 1
            },
            error = {
                emitProjectUiState(isRefresh = isRefresh,showLoading = false)
            }
        )
    }

    private fun emitProjectUiState(
        isRefresh: Boolean = false,
        showEnd: Boolean = false,
        showLoading: Boolean = false,
        projectTree: List<ProjectChapter>? = null,
        projectList: List<Article>? = null
    ) {
        _uiState.value = ProjectUiModel(isRefresh,showEnd,showLoading,projectTree,projectList)
    }

    data class ProjectUiModel(
        val isRefresh: Boolean,
        val showEnd: Boolean,
        val showLoading: Boolean,
        var projectTree: List<ProjectChapter>?,
        var projectList: List<Article>?
    )
}