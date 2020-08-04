package com.example.mvvm_wan_kot.ui.main.navigation

import androidx.core.view.isGone
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.base.BaseVMFragment
import com.example.mvvm_wan_kot.common.ext.toIntPx
import com.example.mvvm_wan_kot.common.view.SpaceItemDecoration
import com.example.mvvm_wan_kot.ui.common.webview.WebViewActivity
import com.example.mvvm_wan_kot.ui.main.navigation.adapter.NavigationAdapter
import kotlinx.android.synthetic.main.fragment_navigation.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class NavigationFragment : BaseVMFragment<NavigationViewModel>() {

    override fun getLayoutResId() = R.layout.fragment_navigation

    override fun initVM(): NavigationViewModel =  getViewModel()

    private val navigationAdapter by lazy { NavigationAdapter() }
    private var currentPosition = 0

    override fun initView() {
        mBinding.run {
            setVariable(BR.vm,mViewModel)
            setVariable(BR.adapter,navigationAdapter)
        }
        navigationRecyclerView.run {
            addItemDecoration(SpaceItemDecoration(0,0,12.toIntPx(),0))
            setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
                if (scrollY < oldScrollY) {
                    navigationFloatTitle.text = navigationAdapter.data[currentPosition].name
                }
                val lm = layoutManager as LinearLayoutManager
                val nextView = lm.findViewByPosition(currentPosition + 1)
                if (nextView != null) {
                    navigationFloatTitle.y = if (nextView.top < navigationFloatTitle.measuredHeight) {
                        (nextView.top - navigationFloatTitle.measuredHeight).toFloat()
                    } else {
                        0f
                    }
                }
                currentPosition = lm.findFirstVisibleItemPosition()
                if (scrollY > oldScrollY) {
                    navigationFloatTitle.text = navigationAdapter.data[currentPosition].name
                }
            }
        }
        navigationAdapter.onTagClickListener = {
            WebViewActivity.goDetailActivity(it)
        }
    }

    override fun initData() {
        mViewModel.getNavigationList(true)
    }

    override fun startObserve() {
        mViewModel.apply {
            uiState.observe(this@NavigationFragment, Observer {
                it.navigationList?.let { list ->
                    navigationFloatTitle.isGone = list.isEmpty()
                    navigationFloatTitle.text = list[0].name
                    navigationAdapter.setList(list)
                }
            })
        }
    }
}