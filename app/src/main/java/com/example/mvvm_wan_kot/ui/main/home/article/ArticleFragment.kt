package com.example.mvvm_wan_kot.ui.main.home.article

import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.base.BaseVMFragment
import com.example.mvvm_wan_kot.common.ext.showToast
import com.example.mvvm_wan_kot.common.view.SpaceItemDecoration
import com.example.mvvm_wan_kot.ui.main.adapter.HomeArticleAdapter
import kotlinx.android.synthetic.main.fragment_article.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ArticleFragment : BaseVMFragment<ArticleViewModel>() {
    override fun getLayoutResId() = R.layout.fragment_article

    override fun initVM(): ArticleViewModel = getViewModel()

    private val homeArticleAdapter by lazy { HomeArticleAdapter() }

    override fun initView() {
        mBinding.run {
            setVariable(BR.vm, mViewModel)
            setVariable(BR.adapter, homeArticleAdapter)
        }
        homeArticleRecyclerView.addItemDecoration(SpaceItemDecoration(0, 0, 20, 0))
        homeArticleAdapter.run {
            loadMoreModule.isEnableLoadMore = true
            loadMoreModule.setOnLoadMoreListener {
                mViewModel.getArticleList(false)
            }
            setOnItemChildClickListener { _, view, position ->
                if (view.id == R.id.collect) {
                    mViewModel.collect(data[position].collect, data[position].id)
                    data[position].collect = !data[position].collect
                    notifyItemChanged(position)
                }
            }
            setOnItemClickListener { _, _, position ->
                val link = data[position].link

            }
            setEmptyView(R.layout.adapter_empty_view)
        }
    }

    override fun initData() {
        mViewModel.getArticleList(true)
    }

    override fun startObserve() {
        mViewModel.apply {
            uiState.observe(this@ArticleFragment, Observer {
                it.collectSuccess?.let { str ->
                    activity?.showToast(str)
                }
                if (it.showEnd) homeArticleAdapter.loadMoreModule.loadMoreEnd()
                if (it.showDialogLoading) showProgressDialog() else hideProgressDialog()
                it.articleList?.let { list ->
                    homeArticleAdapter.run {
                        if (it.isRefresh) setList(list)
                        else {
                            addData(list)
                            loadMoreModule.loadMoreComplete()
                        }
                    }
                }
            })
        }
    }
}