package com.example.mvvm_wan_kot.ui.history

import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.adapter.NavIconType
import com.example.mvvm_wan_kot.common.base.BaseVMActivity
import com.example.mvvm_wan_kot.common.ext.setOnClickListener
import com.example.mvvm_wan_kot.common.ext.setToolbar
import com.example.mvvm_wan_kot.common.ext.showToast
import com.example.mvvm_wan_kot.common.ext.toIntPx
import com.example.mvvm_wan_kot.common.view.SpaceItemDecoration
import com.example.mvvm_wan_kot.ui.common.webview.WebViewActivity
import com.example.mvvm_wan_kot.ui.history.adapter.HistoryAdapter
import kotlinx.android.synthetic.main.activity_history.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class HistoryActivity : BaseVMActivity<HistoryViewModel>() {
    override fun getLayoutResId(): Int {
        return R.layout.activity_history
    }

    private val historyAdapter by lazy {
        HistoryAdapter()
    }

    override fun initVM(): HistoryViewModel = getViewModel()

    override fun initView() {
        setToolbar(getString(R.string.history_title),NavIconType.BACK)
        mBinding.run {
            setVariable(BR.vm,mViewModel)
            setVariable(BR.adapter,historyAdapter)
        }
        historyRecyclerView.addItemDecoration(SpaceItemDecoration(0,0,10.toIntPx(),0))
        historyAdapter.run {
            recyclerView = historyRecyclerView
            val emptyView = layoutInflater.inflate(R.layout.adapter_empty_view, recyclerView, false)
            setEmptyView(emptyView)
            setOnItemChildClickListener { _, view, position ->
                if (view.id == R.id.delete){
                    mViewModel.deleteHistory(data[position])
                }
            }
            setOnItemClickListener { _, _, position ->
                WebViewActivity.goDetailActivity(data[position])
            }
        }
        setOnClickListener(historyDeleteAll){
            if (historyAdapter.data.size !=0){
                mViewModel.deleteHistory(*historyAdapter.data.toTypedArray())
            }else{
                showToast("没有阅读记录")
            }
        }
    }

    override fun initData() {
        mViewModel.getAllHistory(true)
    }

    override fun startObserve() {
        mViewModel.apply {
            uiState.observe(this@HistoryActivity, Observer {
                it.historyList?.let {list ->
                    historyAdapter.setList(list)
                }
                it.deleteToast?.let {toast ->
                    showToast(toast)
                    mViewModel.getAllHistory(true)
                }
                if (it.showDialogLoading) showProgressDialog() else hideProgressDialog()
            })
        }
    }
}