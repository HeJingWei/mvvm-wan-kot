package com.example.mvvm_wan_kot.ui.main.system

import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.base.BaseVMFragment
import com.example.mvvm_wan_kot.common.ext.toIntPx
import com.example.mvvm_wan_kot.common.utils.ActivityManager
import com.example.mvvm_wan_kot.common.view.SpaceItemDecoration
import com.example.mvvm_wan_kot.ui.main.system.adapter.SysAdapter
import kotlinx.android.synthetic.main.fragment_system.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SystemFragment : BaseVMFragment<SystemViewModel>() {

    override fun getLayoutResId() = R.layout.fragment_system

    override fun initVM(): SystemViewModel =  getViewModel()

    private val sysAdapter by lazy { SysAdapter() }

    override fun initView() {
        mBinding.run {
            setVariable(BR.vm,mViewModel)
            setVariable(BR.adapter,sysAdapter)
        }
        sysRecyclerView.addItemDecoration(SpaceItemDecoration(0,0,10.toIntPx(),0))
        sysAdapter.run {
            setEmptyView(R.layout.adapter_empty_view)
            setOnItemClickListener { _, _, position ->
                val bean = data[position]
                val map = HashMap<String,Any>()
                map[SystemListActivity.SYS_BEAN] = bean
                ActivityManager.start(SystemListActivity::class.java,map)
            }
        }
    }

    override fun initData() {
        mViewModel.getSysList()
    }

    override fun startObserve() {
        mViewModel.apply {
            uiState.observe(this@SystemFragment, Observer {
                it.sysList?.let {list ->
                    sysAdapter.setList(list)
                }
            })
        }
    }
}