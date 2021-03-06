package com.example.mvvm_wan_kot.ui.main.home.wxproject

import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.base.BaseVMFragment
import com.example.mvvm_wan_kot.common.ext.showToast
import com.example.mvvm_wan_kot.common.ext.toIntPx
import com.example.mvvm_wan_kot.common.view.SpaceItemDecoration
import com.example.mvvm_wan_kot.model.bean.Article
import com.example.mvvm_wan_kot.ui.common.webview.WebViewActivity
import com.example.mvvm_wan_kot.ui.main.adapter.CategoryAdapter
import com.example.mvvm_wan_kot.ui.main.adapter.HomeWxProjectAdapter
import kotlinx.android.synthetic.main.fragment_wx_project.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class WxProjectFragment : BaseVMFragment<WxProjectViewModel>() {
    override fun getLayoutResId(): Int {
        return R.layout.fragment_wx_project
    }

    private val homeProjectAdapter by lazy {
        HomeWxProjectAdapter()
    }
    private val categoryAdapter by lazy {
        CategoryAdapter()
    }

    override fun initVM(): WxProjectViewModel {
        return getViewModel()
    }

    override fun initView() {
        mBinding.run {
            setVariable(BR.vm, mViewModel)
            setVariable(BR.adapter, homeProjectAdapter)
            setVariable(BR.h_adapter, categoryAdapter)
        }
        homeProjectAdapter.run {
            loadMoreModule.isEnableLoadMore = true
            loadMoreModule.setOnLoadMoreListener {
                mViewModel.getProjectList(false)
            }
            setOnItemChildClickListener { _, view, position ->
                if (view.id == R.id.homeProjectCollect) {
                    mViewModel.collect(data[position].collect, data[position].id)
                    data[position].collect = !data[position].collect
                    notifyItemChanged(position)
                }
            }
            setOnItemClickListener { _, _, position ->
                WebViewActivity.goDetailActivity(data[position])
            }
            setEmptyView(R.layout.adapter_empty_view)
        }
        projectRecyclerView.addItemDecoration(SpaceItemDecoration(0,0,20,0))
        projectCategoryRecyclerView.addItemDecoration(SpaceItemDecoration(0,0,0,8.toIntPx()))
        categoryAdapter.setOnItemClickListener { _, _, position ->
            val id = categoryAdapter.data[position].id
            if (id != mViewModel.chapterId){
                categoryAdapter.check(position)
                mViewModel.chapterId = id
                mViewModel.getProjectList(true)
            }
        }
    }

    override fun initData() {
        mViewModel.run {
            getProjectChapters()
        }
    }

    override fun startObserve() {
        mViewModel.apply {
            uiState.observe(this@WxProjectFragment, Observer {
                it.projectChapters?.let { list ->
                    categoryAdapter.setList(list)
                    chapterId = list[0].id
                    getProjectList(true)
                }
                it.projectList?.let { list ->
                    homeProjectAdapter.run {
                        if (it.isRefresh) setList(list) else {
                            addData(list)
                            loadMoreModule.loadMoreComplete()
                        }
                        if (it.showEnd) loadMoreModule.loadMoreEnd()
                    }
                }
                if (it.showDialogLoading) showProgressDialog() else hideProgressDialog()
                it.collectToast?.let {collectToast ->
                   activity?.showToast(collectToast)
                }
            })
        }
    }
}