package com.example.mvvm_wan_kot.ui.main.home.project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.App
import com.example.mvvm_wan_kot.common.base.BaseViewModel
import com.example.mvvm_wan_kot.model.bean.Project
import com.example.mvvm_wan_kot.model.bean.ProjectChapter
import com.example.mvvm_wan_kot.ui.common.CollectOperateRepository

class ProjectViewModel(
    val projectRepository: ProjectRepository,
    val collectOperateRepository: CollectOperateRepository
) : BaseViewModel() {
    private val _uiState = MutableLiveData<ProjectUiModel>()
    val uiState: LiveData<ProjectUiModel>
        get() = _uiState

    private var currPage = 1
    var chapterId = 0

    val refreshData : () -> Unit = {
        getProjectList(true)
    }

    fun getProjectChapters() {
        launchOnUI(block = {
            val res = projectRepository.getProjectChapters()
            emitProjectUiState(projectChapters = res)
        })
    }

    fun getProjectList(isRefresh: Boolean) {
        launchOnUI(block = {
            if (isRefresh) currPage = 1
            val res = projectRepository.getProjectList(chapterId, currPage)
            if (res.offset >= res.total) {
                emitProjectUiState(showEnd = true, showLoading = false)
            }
            emitProjectUiState(isRefresh = isRefresh, showLoading = false, projectList = res.datas)
            currPage += 1
        }, error = {
            emitProjectUiState(isRefresh = isRefresh, showLoading = false)
        })
    }

    fun collect(isCollect: Boolean, id: Int) {
        emitProjectUiState(showDialogLoading = true)
        launchOnUI(block = {
            if (!isCollect) collectOperateRepository.collect(id) else collectOperateRepository.collectCancel(
                id
            )
            emitProjectUiState(
                showDialogLoading = false,
                collectToast = App.instance.getString(R.string.operate_success)
            )
        }, error = {
            emitProjectUiState(
                showDialogLoading = false,
                collectToast = App.instance.getString(R.string.operate_fail)
            )
        })
    }


    private fun emitProjectUiState(
        collectToast: String? = null,
        showDialogLoading: Boolean = false,
        isRefresh: Boolean = false,
        showLoading: Boolean = false,
        showEnd: Boolean = false,
        projectList: List<Project>? = null,
        projectChapters: List<ProjectChapter>? = null
    ) {
        _uiState.value = ProjectUiModel(
            collectToast,
            showDialogLoading,
            isRefresh,
            showLoading,
            showEnd,
            projectList,
            projectChapters
        )
    }

    data class ProjectUiModel(
        var collectToast: String?,
        val showDialogLoading: Boolean,
        val isRefresh: Boolean,
        val showLoading: Boolean,
        val showEnd: Boolean,
        var projectList: List<Project>?,
        var projectChapters: List<ProjectChapter>?
    )
}