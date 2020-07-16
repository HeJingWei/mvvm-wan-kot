package com.example.mvvm_wan_kot.ui.setting

import androidx.lifecycle.Observer
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.base.BaseVMActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SettingActivity : BaseVMActivity<SettingViewModel>() {

    override fun getLayoutResId() = R.layout.activity_setting

    override fun initVM(): SettingViewModel = getViewModel()

    override fun initView() {

    }

    override fun initData() {
        mViewModel.isExitLogin()
    }

    override fun startObserve() {
        mViewModel.apply {
            uiState.observe(this@SettingActivity, Observer {

            })
        }
    }
}