package com.example.mvvm_wan_kot.ui.collect

import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.adapter.NavIconType
import com.example.mvvm_wan_kot.common.base.BaseVMActivity
import com.example.mvvm_wan_kot.common.ext.setToolbar
import com.example.mvvm_wan_kot.common.ext.showToast
import com.example.mvvm_wan_kot.common.view.SpaceItemDecoration
import com.example.mvvm_wan_kot.ui.collect.adapter.CollectAdapter
import com.example.mvvm_wan_kot.ui.common.webview.WebViewActivity
import kotlinx.android.synthetic.main.activity_collect.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class CollectActivity : BaseVMActivity<CollectViewModel>() {
    override fun getLayoutResId(): Int {
        return R.layout.activity_collect
    }

    override fun initVM(): CollectViewModel = getViewModel()

    private val collectAdapter by lazy { CollectAdapter() }

    var pos = -1

    override fun initView() {
        mBinding.run {
            setVariable(BR.vm, mViewModel)
            setVariable(BR.adapter, collectAdapter)
        }
        setToolbar(getString(R.string.collect_title), NavIconType.BACK)

        collectAdapter.run {
            recyclerView = collectRecycleView
            val emptyView = layoutInflater.inflate(R.layout.adapter_empty_view, recyclerView, false)
            setEmptyView(emptyView)
            setOnItemClickListener { _, _, position ->
                WebViewActivity.goDetailActivity(data[position])
            }
            loadMoreModule.setOnLoadMoreListener {
                mViewModel.getCollectList(false)
            }
            setOnItemChildClickListener { _, view, position ->
                if (view.id == R.id.collectCancel) {
                    mViewModel.collectCancel(collectAdapter.data[position].originId)
                    pos = position
                }
            }
            loadMoreModule.isEnableLoadMore = true
        }
        collectRecycleView.addItemDecoration(SpaceItemDecoration(0, 0, 20, 0))
    }

    override fun initData() {
        mViewModel.getCollectList(true)
    }

    override fun startObserve() {
        mViewModel.run {
            uiState.observe(this@CollectActivity, Observer {
                if (it.showLoadingDialog) showProgressDialog() else hideProgressDialog()
                it.collectList?.let { list ->
                    collectAdapter.run {
                        if (it.isRefresh) setList(list)
                        else {
                            addData(list)
                            loadMoreModule.loadMoreComplete()
                        }
                    }
                }
                if (it.showEnd) collectAdapter.loadMoreModule.loadMoreEnd()
                it.cancelSuccess?.let { toastStr ->
                    showToast(toastStr)
                    collectAdapter.run {
                        data.removeAt(pos)
                        notifyDataSetChanged()
                    }
                }
            })
        }
    }
}