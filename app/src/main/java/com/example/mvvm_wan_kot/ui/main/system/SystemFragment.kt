package com.example.mvvm_wan_kot.ui.main.system

import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.base.BaseVMFragment
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SystemFragment : BaseVMFragment<SystemViewModel>() {

    override fun getLayoutResId() = R.layout.fragment_system

    override fun initVM(): SystemViewModel =  getViewModel()

    override fun initView() {

    }

    override fun initData() {

    }

    override fun startObserve() {

    }
}