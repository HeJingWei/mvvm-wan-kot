package com.example.mvvm_wan_kot.ui.main.navigation

import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.base.BaseVMFragment
import org.koin.androidx.viewmodel.ext.android.getViewModel

class NavigationFragment : BaseVMFragment<NavigationViewModel>() {

    override fun getLayoutResId() = R.layout.fragment_navigation

    override fun initVM(): NavigationViewModel =  getViewModel()

    override fun initView() {

    }

    override fun initData() {

    }

    override fun startObserve() {

    }
}