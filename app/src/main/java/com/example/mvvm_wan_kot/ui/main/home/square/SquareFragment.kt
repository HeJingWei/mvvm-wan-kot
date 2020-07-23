package com.example.mvvm_wan_kot.ui.main.home.square

import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.base.BaseVMFragment
import com.example.mvvm_wan_kot.common.ext.showToast
import com.example.mvvm_wan_kot.ui.main.adapter.HomeSquareAdapter
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SquareFragment : BaseVMFragment<SquareViewModel>() {
    override fun getLayoutResId() = R.layout.fragment_square

    private val squareAdapter by lazy { HomeSquareAdapter() }

    override fun initVM(): SquareViewModel = getViewModel()

    override fun initView() {
        mBinding.run {
            setVariable(BR.vm, mViewModel)
            setVariable(BR.adapter, squareAdapter)
        }

        squareAdapter.run {
            setOnItemClickListener { adapter, view, position ->
                val link = squareAdapter.data[position].link
                this@SquareFragment.activity?.showToast(link)
            }
            setOnItemClickListener { _, view, position ->
                when (view.id) {
                    R.id.collect -> {
                        mViewModel.collect(data[position].collect, data[position].id)
                        data[position].collect = !data[position].collect
                        notifyItemChanged(position)
                    }
                }
            }
            loadMoreModule.setOnLoadMoreListener {
                loadMore()
            }
            setEmptyView(R.layout.adapter_empty_view)
        }
    }

    private fun loadMore() {
        mViewModel.getSquareList(false)
    }

    override fun initData() {
        mViewModel.getSquareList(true)
    }

    override fun startObserve() {
        mViewModel.uiState.observe(viewLifecycleOwner, Observer {
            it.article?.let { list ->
                squareAdapter.run {
                    if (it.isRefresh) {
                        setList(list)
                    } else {
                        addData(list)
                        loadMoreModule.loadMoreComplete()
                    }
                }
            }
            if (it.showEnd) squareAdapter.loadMoreModule.loadMoreEnd()
            if (it.showDialogLoading) showProgressDialog() else hideProgressDialog()
            it.collectToast?.let { collectToast ->
                activity?.showToast(collectToast)
            }
        })
    }

}