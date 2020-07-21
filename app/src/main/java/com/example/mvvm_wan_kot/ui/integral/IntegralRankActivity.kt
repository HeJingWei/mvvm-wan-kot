package com.example.mvvm_wan_kot.ui.integral

import android.view.LayoutInflater
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.adapter.NavIconType
import com.example.mvvm_wan_kot.common.base.BaseVMActivity
import com.example.mvvm_wan_kot.common.ext.setToolbar
import com.example.mvvm_wan_kot.common.view.SpaceItemDecoration
import com.example.mvvm_wan_kot.ui.integral.adapter.IntegralRankAdapter
import kotlinx.android.synthetic.main.activity_integral_rank.*
import kotlinx.android.synthetic.main.item_integral_rank_item.view.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class IntegralRankActivity : BaseVMActivity<IntegralViewModel>() {

    override fun getLayoutResId() = R.layout.activity_integral_rank

    override fun initVM(): IntegralViewModel = getViewModel()

    private val integralRankAdapter by lazy { IntegralRankAdapter() }

    override fun initView() {
        mBinding.run {
            setVariable(BR.vm, mViewModel)
            setVariable(BR.adapter, integralRankAdapter)
        }

        setToolbar(getString(R.string.integral_rank), NavIconType.BACK)

        val headView =
            LayoutInflater.from(this).inflate(R.layout.item_integral_rank_item, null, false)
        headView.run {
            integralRank.text = "排名"
            integralRankUser.text = "用户"
            integralRankLevel.text = "等级"
            integralRankPoint.text = "积分"
        }

        integralRankAdapter.run {
            addHeaderView(headView)
            loadMoreModule.isEnableLoadMore = true
            loadMoreModule.setOnLoadMoreListener {
                mViewModel.getIntegralRankList(false)
            }
        }

        integralRankRecycleView.addItemDecoration(SpaceItemDecoration(2, 0, 0, 0))
    }

    override fun initData() {
        mViewModel.getIntegralRankList(true)
    }

    override fun startObserve() {
        mViewModel.run {
            uiState.observe(this@IntegralRankActivity, Observer {
                it.integralRankList?.let { list ->
                    integralRankAdapter.run {
                        if (it.isRefresh) setList(list)
                        else {
                            addData(list)
                            loadMoreModule.loadMoreComplete()
                        }
                    }
                }
                if (it.showRankEnd) integralRankAdapter.loadMoreModule.loadMoreEnd()
            })
        }
    }
}