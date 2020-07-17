package com.example.mvvm_wan_kot.ui.integral

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.adapter.NavIconType
import com.example.mvvm_wan_kot.common.base.BaseVMActivity
import com.example.mvvm_wan_kot.common.ext.setOnClickListener
import com.example.mvvm_wan_kot.common.ext.setToolbar
import com.example.mvvm_wan_kot.common.utils.ActivityManager
import com.example.mvvm_wan_kot.common.view.SpaceItemDecoration
import com.example.mvvm_wan_kot.ui.integral.adapter.IntegralItemAdapter
import kotlinx.android.synthetic.main.activity_integral.*
import kotlinx.android.synthetic.main.activity_integral_headview.view.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class IntegralActivity : BaseVMActivity<IntegralViewModel>() {
    lateinit var headView: View

    private val integralItemAdapter by lazy { IntegralItemAdapter() }

    override fun getLayoutResId() = R.layout.activity_integral

    override fun initVM(): IntegralViewModel = getViewModel()

    override fun initView() {
        mBinding.run {
            setVariable(BR.vm, mViewModel)
            setVariable(BR.adapter, integralItemAdapter)
        }
        setToolbar(getString(R.string.integral_title), NavIconType.BACK)
        headView =
            LayoutInflater.from(this).inflate(R.layout.activity_integral_headview, null, false)
        DataBindingUtil.bind<ViewDataBinding>(headView)?.setVariable(BR.vm, mViewModel)
        setOnClickListener(headView.integralGoRank) {
            ActivityManager.start(IntegralRankActivity::class.java)
        }
        integralItemAdapter.run {
            addHeaderView(headView)
            loadMoreModule.isEnableLoadMore = true
            loadMoreModule.setOnLoadMoreListener {
                mViewModel.getIntegralList(false)
            }
        }
        integralRecycleView.addItemDecoration(SpaceItemDecoration(2, 0, 0, 0))
    }

    override fun initData() {
        mViewModel.run {
            getUserIntegral()
            getIntegralList(false)
        }
    }

    override fun startObserve() {
        mViewModel.apply {
            uiState.observe(this@IntegralActivity, Observer {
                it.integralList?.let { list ->
                    integralItemAdapter.run {
                        if (it.isRefresh) {
                            setList(list)
                        } else {
                            addData(list)
                            loadMoreModule.loadMoreComplete()
                        }
                        if (it.showEnd) loadMoreModule.loadMoreEnd()
                    }
                }
            })
        }
    }
}