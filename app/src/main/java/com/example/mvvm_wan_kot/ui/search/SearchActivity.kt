package com.example.mvvm_wan_kot.ui.search

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.base.BaseVMActivity
import com.example.mvvm_wan_kot.common.ext.setOnClickListener
import com.example.mvvm_wan_kot.common.ext.toIntPx
import com.example.mvvm_wan_kot.common.view.SpaceItemDecoration
import com.example.mvvm_wan_kot.ui.common.WebViewActivity
import com.example.mvvm_wan_kot.ui.common.adapter.SearchTagAdapter
import com.example.mvvm_wan_kot.ui.search.adapter.SearchHistoryAdapter
import com.example.mvvm_wan_kot.ui.search.adapter.SearchResAdapter
import kotlinx.android.synthetic.main.activity_search.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SearchActivity : BaseVMActivity<SearchViewModel>() {

    override fun getLayoutResId() = R.layout.activity_search

    override fun initVM(): SearchViewModel = getViewModel()

    private val searchResAdapter by lazy {
        SearchResAdapter()
    }
    private val searchHistoryAdapter by lazy {
        SearchHistoryAdapter()
    }

    override fun initView() {
        mBinding.run {
            setVariable(BR.vm, mViewModel)
            setVariable(BR.adapter, searchResAdapter)
            setVariable(BR.h_adapter, searchHistoryAdapter)
        }
        searchResRecyclerView.addItemDecoration(SpaceItemDecoration(0, 0, 10.toIntPx(), 0))
        searchResAdapter.run {
            loadMoreModule.isEnableLoadMore = true
            setOnItemClickListener { _, _, position ->
                val link = data[position].link
                WebViewActivity.goDetailActivity(link)
            }
            setEmptyView(R.layout.adapter_empty_view)
            loadMoreModule.setOnLoadMoreListener {
                mViewModel.queryArticleList()
            }
        }
        searchHistoryAdapter.setOnItemChildClickListener { _, view, position ->
            if (view.id == R.id.searchHistoryDelete) {

            }
        }
        setOnClickListener(searchBack, searchStart) {
            when (this) {
                searchBack ->
                    if (searchResRecyclerView.isVisible) {
                        searchResRecyclerView.visibility = View.GONE
                        mViewModel.currPage = 0
                        searchResAdapter.setList(null)
                    } else
                        onBackPressed()
                searchStart -> {
                    mViewModel.run {
                        key = searchContent.text.toString()
                        currPage = 0
                        searchResAdapter.setList(null)
                        queryArticleList()
                    }
                }
            }
        }
    }

    override fun initData() {
        mViewModel.getHotKey()
    }

    override fun startObserve() {
        mViewModel.apply {
            uiState.observe(this@SearchActivity, Observer {
                it.hotKeyList?.let { list ->
                    searchFlowLayout.adapter = SearchTagAdapter(list)
                    searchFlowLayout.setOnTagClickListener { _, position, _ ->
                        searchContent.setText(list[position].name)
                        true
                    }
                }
                it.articleList?.let {
                    list ->
                    searchResAdapter.run {
                        addData(list)
                        loadMoreModule.loadMoreComplete()
                    }
                    searchResRecyclerView.visibility = View.VISIBLE
                }
                it.historyList?.let {
                    list ->
                    searchHistoryAdapter.setList(list)
                }
                if (it.showDialogLoading) showProgressDialog() else hideProgressDialog()
                if (it.showEnd) searchResAdapter.loadMoreModule.loadMoreEnd()
            })
        }
    }
}