package com.example.mvvm_wan_kot.ui.main.home.project

import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.base.BaseVMFragment
import com.example.mvvm_wan_kot.common.view.SpaceItemDecoration
import com.example.mvvm_wan_kot.ui.common.webview.WebViewActivity
import com.example.mvvm_wan_kot.ui.main.adapter.HomeProjectAdapter
import com.example.mvvm_wan_kot.ui.main.adapter.ProjectCategoryAdapter
import kotlinx.android.synthetic.main.fragment_project.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ProjectFragment : BaseVMFragment<ProjectViewModel>() {
    override fun getLayoutResId() = R.layout.fragment_project

    private val projectCategoryAdapter by lazy { ProjectCategoryAdapter() }
    private val homeProjectAdapter by lazy { HomeProjectAdapter() }

    override fun initVM(): ProjectViewModel = getViewModel()

    override fun initView() {
        mBinding.run {
            setVariable(BR.vm, mViewModel)
            setVariable(BR.adapter, homeProjectAdapter)
            setVariable(BR.h_adapter, projectCategoryAdapter)
        }
        homeProjectRecyclerView.addItemDecoration(SpaceItemDecoration(0, 0, 20, 0))
        homeProjectAdapter.run {
            loadMoreModule.isEnableLoadMore = true
            loadMoreModule.setOnLoadMoreListener {
                mViewModel.getProjectList(false)
            }
            setEmptyView(R.layout.adapter_empty_view)
            setOnItemClickListener { _, _, position ->
                WebViewActivity.goDetailActivity(data[position])
            }
        }
        projectCategoryAdapter.run {
            setOnItemClickListener { _, _, position ->
                setCheckPos(position)
                mViewModel.let {
                    it.cid = data[position].id
                    it.getProjectList(true)
                }
            }
        }
    }

    override fun initData() {
        mViewModel.getProjectTree()
    }

    override fun startObserve() {
        mViewModel.apply {
            uiState.observe(viewLifecycleOwner, Observer {
                it.projectTree?.let { list ->
                    projectCategoryAdapter.setList(list)
                    mViewModel.let {
                        cid = list[0].id
                        getProjectList(true)
                    }
                }
                it.projectList?.let { list ->
                    homeProjectAdapter.run {
                        if (it.isRefresh) setList(list)
                        else {
                            addData(list)
                            loadMoreModule.loadMoreComplete()
                        }
                        if (it.showEnd) homeProjectAdapter.loadMoreModule.loadMoreEnd()
                    }
                }
            })
        }
    }
}