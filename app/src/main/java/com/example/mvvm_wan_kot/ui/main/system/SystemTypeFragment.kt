package com.example.mvvm_wan_kot.ui.main.system

import android.os.Bundle
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.base.BaseVMFragment
import com.example.mvvm_wan_kot.common.ext.showToast
import com.example.mvvm_wan_kot.common.ext.toIntPx
import com.example.mvvm_wan_kot.common.view.SpaceItemDecoration
import com.example.mvvm_wan_kot.ui.common.webview.WebViewActivity
import com.example.mvvm_wan_kot.ui.main.adapter.HomeArticleAdapter
import kotlinx.android.synthetic.main.fragment_systemtype.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SystemTypeFragment : BaseVMFragment<SystemViewModel>() {
    override fun getLayoutResId() = R.layout.fragment_systemtype

    override fun initVM(): SystemViewModel = getViewModel()

    private val homeArticleAdapter by lazy { HomeArticleAdapter() }

    private val cid by lazy { arguments?.get(CID) }

    companion object {
        private const val CID = "cid"
        fun newInstance(cid: Int): SystemTypeFragment {
            val fragment = SystemTypeFragment()
            val bundle = Bundle()
            bundle.putInt(CID, cid)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun initView() {
        mBinding.run {
            setVariable(BR.vm, mViewModel)
            setVariable(BR.adapter, homeArticleAdapter)
        }
        systemTypeRecyclerView.addItemDecoration(SpaceItemDecoration(0, 0, 1.toIntPx(), 0))
        homeArticleAdapter.run {
            loadMoreModule.isEnableLoadMore = true
            setEmptyView(R.layout.adapter_empty_view)
            loadMoreModule.setOnLoadMoreListener {
                mViewModel.getSysListByCid(false)
            }
            setOnItemClickListener { _, _, position ->
                WebViewActivity.goDetailActivity(data[position])
            }
            setOnItemChildClickListener { _, view, position ->
                if (view.id == R.id.collect) {
                    mViewModel.collect(data[position].collect, data[position].id)
                    data[position].collect = !data[position].collect
                    notifyItemChanged(position)
                }
            }
        }
    }

    override fun initData() {
        mViewModel.cid = cid as Int
        mViewModel.getSysListByCid(true)
    }

    override fun startObserve() {
        mViewModel.apply {
            uiState.observe(this@SystemTypeFragment, Observer {
                it.articleList?.let { list ->
                    homeArticleAdapter.run {
                        if (it.isRefresh) setList(list)
                        else {
                            addData(list)
                            loadMoreModule.loadMoreComplete()
                        }
                    }
                }
                if (it.showEnd) homeArticleAdapter.loadMoreModule.loadMoreEnd()
                it.collectSuccess?.let { str ->
                    activity?.showToast(str)
                }
                if (it.showDialogLoading) showProgressDialog() else hideProgressDialog()
            })
        }
    }
}